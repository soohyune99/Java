package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dao.TicketingDAO;
import dao.WebConnecter;
import vo.TicketingVO;

public class Kobis {

	private WebDriver driver;

	public static String urlBoxOffice = "https://www.kobis.or.kr/kobis/business/stat/boxs/findFormerBoxOfficeList.do";
	public static String urlThreaterSchedule = "https://www.kobis.or.kr/kobis/business/mast/thea/findTheaterSchedule.do";
	public static String urlSearch = "https://www.kobis.or.kr/kobis/business/main/main.do";
	public static String urlAdvanceTicketSales = "https://kobis.or.kr/kobis/business/stat/boxs/findRealTicketList.do";
	
	WebConnecter webConnecter = new WebConnecter();
	TicketingDAO ticketingDAO = new TicketingDAO();
	Scanner sc = new Scanner(System.in);
	Kiosk kiosk = new Kiosk();

	// 역대 영화 크롤링
	public void crawlingGenre() {
		driver = WebConnecter.getConnects();

		String selector = "";
		int count = 50;

		driver.get(urlBoxOffice);
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		for (WebElement webElement : driver.findElements(By.cssSelector("#td_movie a"))) {
			kiosk.movies.add(webElement.getAttribute("title"));
		}

		for (int i = 0; i < count; i++) {
			selector = "tr#tr_" + i + " td#td_movie > span > a";
			driver.findElement(By.cssSelector(selector)).click();

			kiosk.informations.add(driver.findElement(By.cssSelector("div.ovf dd:nth-of-type(4)")).getText());
			
			driver.findElement(By.cssSelector("a.close")).click();
		}
		
		driver.quit();
	}
	
	// 영화 추천
	public void recommendGenre() {
		String selectGenre = "";
		String[] arrSelect = null;
		int i = 0;
		
		for (String filter : kiosk.filters) {
			System.out.print(++i + ". " + filter + "\t\t");
			if(i % 3 == 0) {System.out.println();}
		}
		
		System.out.println();
		System.out.print("추천받고 싶으신 태그를 모두 선택해주세요 : ");
		selectGenre = sc.nextLine();
		arrSelect = selectGenre.split(" ");
		
		for (int k = 0; k < kiosk.informations.size(); k++) {
			int index = 0;
			boolean check = false;
			
			while(index < arrSelect.length) {
				check = kiosk.informations.get(k).contains(kiosk.genres.get(Integer.valueOf(arrSelect[index])));
				if(!check) {break;}
				index++;
			}
			if(!check) {continue;}
			System.out.println("<" + kiosk.movies.get(k) + ">");
			System.out.println(kiosk.informations.get(k));
		}
	}

	// 예매하기
	public TicketingVO reserveTicket(TicketingVO ticketingVO) {
		driver = WebConnecter.getConnects();
		
		ArrayList<String> reservedSeats = null;
		kiosk.threaters = new ArrayList<String>();
		kiosk.threaterNumbers = new ArrayList<String>();
		kiosk.movieNames = new ArrayList<String>();
		kiosk.times = new ArrayList<String>();
		
		String selector = "";
		int seq = 0, choice = 0;
		boolean input = false;

		driver.get(urlThreaterSchedule);
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}
		
		// 광역
		for (WebElement webElement : driver.findElements(By.cssSelector("div.fl.step1.on > ul > li"))) {
			System.out.print(++seq + ". ");
			System.out.println(webElement.getText());
			kiosk.cities.add(webElement.getText());
		}

		System.out.print("원하는 지역 선택하세요 : ");
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.cities.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");

		selector = "div.fl.step1.on ul li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 기초
		seq = 0;
		for (WebElement webElement : driver.findElements(By.cssSelector("#sBasareaCd li label"))) {
			System.out.print(++seq + ". ");
			System.out.println(webElement.getText());
			kiosk.locations.add(webElement.getText());
		}

		System.out.print("원하는 지역을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.locations.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");

		selector = "#sBasareaCd li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 영화관
		try {
			seq = 0;
			for (WebElement webElement : driver.findElements(By.cssSelector("ul#sTheaCd li label"))) {
				System.out.print(++seq + ". ");
				System.out.println(webElement.getText());
				kiosk.threaters.add(webElement.getText());
			}
		} catch (NoSuchElementException e) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		} catch (Exception ee) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		}
		
		if(kiosk.threaters.size() == 0) {
			System.out.println("※해당 지역에 영화관이 없습니다.※");
			return null;
		}
		
		System.out.print("원하는 영화관을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.threaters.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTheater(kiosk.threaters.get(choice - 1));

		selector = "ul#sTheaCd li:nth-child(" + choice + ") label";
		driver.findElement(By.cssSelector(selector)).click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}

		// 상영관, 영화 이름
		try {
			seq = 0;
			for (WebElement webElement : driver.findElements(By.cssSelector("ul#schedule li div.tit"))) {
				System.out.print("======" + ++seq + ". =======\n");
				System.out.println(webElement.getText());
				System.out.println("=================");

				kiosk.threaterNumbers.add(webElement.findElement(By.cssSelector("span.screen")).getText());
				kiosk.movieNames.add(webElement.findElement(By.cssSelector("a")).getText());
				}
			} catch (NoSuchElementException e) {
				System.out.println("※다른 영화관을 이용해주세요※");
				return null;
			} catch (Exception ee) {
				System.out.println("※다른 영화관을 이용해주세요※");
				return null;
			}
		
		System.out.println(kiosk.threaterNumbers);
		System.out.println(kiosk.movieNames);
		
		System.out.print("예매를 원하는 영화를 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.movieNames.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTheaterNumber(kiosk.threaterNumbers.get(choice - 1));
		ticketingVO.setTicketingMovie(kiosk.movieNames.get(choice - 1));

		// 영화 시간
		for (WebElement webElement : driver
				.findElements(By.cssSelector("ul#schedule li:nth-child(" + choice + ") div.times label"))) {
			kiosk.times.add(webElement.getText());
		}
		for (int i = 0; i < kiosk.times.size(); i++) {
			System.out.print(i + 1 + "." + "[" + kiosk.times.get(i) + "]" + "  ");
		}

		System.out.println();
		System.out.print("원하는 상영시간을 선택하세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.times.size() + 1) {
				input = !input;
			}			
		}
		System.out.println("------------------------");
		ticketingVO.setTicketingTime(kiosk.times.get(choice - 1));
		
		// 좌석
		reservedSeats = ticketingDAO.getSeats(ticketingVO);
		
		seq = 0;
		if (reservedSeats.size() != 0) {
			for (String reservedSeat : reservedSeats) {
				for (int j = 0; j < kiosk.seats.length; j++) {
					System.out.print(++seq);
					if (reservedSeat.equals(kiosk.seats[j])) {
						System.out.print(".[■" + kiosk.seats[j] + "]\t\t");
					} else {
						System.out.print(".[□" + kiosk.seats[j] + "]\t\t");
					}
					if(seq % 3 == 0) {System.out.println();}
				}
			} 
		} else {
			for (int i = 0; i < kiosk.seats.length; i++) {
				System.out.print(++seq);
				System.out.print(".[□" + kiosk.seats[i] + "]\t\t");
				if(seq % 3 == 0) {System.out.println();}
			}
		}
		
		System.out.println();
		System.out.print("원하시는 좌석을 선택해주세요 : ");
		input = !input;
		while(!input) {
			choice = sc.nextInt();
			if(choice > 0 && choice < kiosk.seats.length + 1) {
				input = !input;
			}			
		}
		ticketingVO.setTicketingSeat(kiosk.seats[choice - 1]);
		
		driver.quit();
		return ticketingVO;
	}
	
	// 영화정보 검색
	public void searchMovie() {
		driver = WebConnecter.getConnects();
		Scanner sc = new Scanner(System.in);
		WebElement input = null, form = null;

		List<WebElement> movieTitleElements = null;
		ArrayList<String> movieTitles = new ArrayList<String>();

		String movieTitle = null;
		
		driver.get(urlSearch);
		input = driver.findElement(By.id("inp_solrSearch"));

		System.out.print("영화 제목 > ");
		input.sendKeys(sc.nextLine());
		input.sendKeys(Keys.RETURN);
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}

		// 전체 게시글
		form = driver.findElement(By.className("tbl_comm"));
		// 제목
		movieTitleElements = form.findElements(By.cssSelector("tr td"));

		for (WebElement webElement : movieTitleElements) {
			movieTitle = webElement.getText();
			movieTitles.add(movieTitle);
		}
		
		if(movieTitles.size() != 0) {
			for (int i = 0; i < movieTitles.size(); i++) {
				 if(movieTitles.get(i).equals("")) {
	                  System.out.print(("정보없음"));
	               }
				System.out.print(movieTitles.get(i) + " | ");
				if((i+1) % 10 == 0 && i != 0) {
					System.out.println();
				}
			}			
		}
	}
	
	// 실시간 예매 순위
	public void advanceTicketSales() {
		driver = WebConnecter.getConnects();
		
		WebElement form = null;
        
        List<WebElement> NumberElement = null;
        List<WebElement> TagElement = null;
        
        ArrayList<String> Tag = new ArrayList<String>();
        ArrayList<String> advanceTicketSales = new ArrayList<String>();
        
        String Numbers = null, NameTags = null;
        
        driver.get(urlAdvanceTicketSales);
     
//           [순위, 영화명, 개봉일, 예매율, 예매매출액, 누적매출액, 예매관객수, 누적관객수] @태그 @ 출력 @@
           TagElement = driver.findElements(By.cssSelector("div.rst_sch th"));
           for (WebElement webElement : TagElement) {
              
              Numbers = webElement.getText();
              Tag.add(Numbers);
              }
           
           System.out.println(Tag);
        
        
     
//        내용 들 출력 @@@
        NumberElement = driver.findElements(By.cssSelector("tr#tr_ td"));
           for (WebElement webElement : NumberElement) {
              
              Numbers = webElement.getText();
              advanceTicketSales.add(Numbers);
        }
        
           for (int i = 0; i < 400; i++) {
              
//               줄 바꿈 
              if(i % 8 == 0) {
                 System.out.println("\n");
              }
              
//              영화 정보 없을 때 
              if(advanceTicketSales.get(i).equals("")) {
                 System.out.print("정보없음");
              }
              
              System.out.print(advanceTicketSales.get(i) + " | " );
              
           }
           
           System.out.println();


	}

}

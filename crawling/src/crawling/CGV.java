package crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * ※ 셀레니움 사용 시 주의사항
 * 브라우저의 url을 변경할 때에는, 미리 WebElement를 담아놓았던 객체의 해당 필드를 전부 다 사용하고 나서 url이 변경되어야 한다.
 * 해당 페이지의 작업은 무조건 다 끝내고 URL을 변경하자!
 * 알고리즘 상 불가능하다면, 기존 URL로 변경 후 작업하자!
 */

public class CGV {
	private WebDriver webDriver;
	public static final String WEB_DRIVER_PATH = "C:/chromedriver_win32/chromedriver.exe";
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	
	public static void main(String[] args) {
		// CGV 객체화
		CGV cgv = new CGV();
		// 내가 들어갈 cgv 페이지를 url로 선언
		String url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
		WebDriver driver = cgv.webDriver;
		Scanner sc = new Scanner(System.in);
		// 극장 수를 담아둘 변수 선언
		String countText = null;
		// 사용자가 입력할 숫자 i 선언, 극장 수 누적 합 total 선언
		int i = 0, total = 0;
		
		// 운영체제에 드라이버 설정
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		// WebDriver의 자식이 ChromeDriver, upcasting이다.
		// WebDriver 객체에 ChromeDriver 대입
		driver = new ChromeDriver();
		
		// 원하는 경로를 브라우저에 전달
		// CGV홈페이지에 들어간다.
		driver.get(url);
		// 컴파일러의 속도가 브라우저의 속도보다 빠르기때문에 thread.sleep()를 해야한다.
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
		// 더보기 버튼 클릭
		// 화면상 나오는 영화는 총 19개, 그러나 더 많은 영화를 가져오기 위해 더보기 버튼 클릭
//		driver.findElement(By.className("btn-more-fontbold")).click();
		// 컴파일러의 속도가 브라우저의 속도보다 빠르기때문에 thread.sleep()를 해야한다.
//		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
		// 예매율
		ArrayList<String> reservationRates = new ArrayList<String>();
		// 영화 제목
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> hrefs = new ArrayList<String>();
		ArrayList<String> genres = new ArrayList<String>();
		
		// 예매율 가져오기
		for (WebElement webElement : driver.findElements(By.cssSelector("strong.percent span"))) {
			reservationRates.add(webElement.getText());
		}
		// 영화 제목 불러오기
		for (WebElement webElement : driver.findElements(By.cssSelector("div.box-contents strong.title"))) {
			names.add(webElement.getText());
		}
		// 영화 이미지 클릭
		for(WebElement webElement : driver.findElements(By.cssSelector("div.box-image a"))) {
			// 영화 19개의 링크를 다 가져온다.
			String href = webElement.getAttribute("href");
			if(href.equals("http://www.cgv.co.kr/movies/?lt=1&ft=0#")) {continue;}
			hrefs.add(webElement.getAttribute("href"));
		}
		
		
		for (String href : hrefs) {
			driver.get(href);
			// 영화 장르 가져오기
			for(WebElement webElement : driver.findElements(By.cssSelector("div.spec dl dt"))) {
				String genre = webElement.getText();
				if(genre.startsWith("장르")) {
					genres.add(genre);
				}
			}
		}
		
		for(int j = 0; j < names.size(); j++) {
			System.out.println(++i + ". " + names.get(j) + ", " + reservationRates.get(j) + ", " + genres.get(j));
		}
		// 사용자가 입력한 번호
		i = sc.nextInt();
		// 인덱스번호와 -1차이
		i--;
		
		driver.get(url);
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
		// 예매하기 버튼 다 가져오기
		List<WebElement> btns = driver.findElements(By.className("link-reservation"));
		
		// 예매하기 버튼은 클릭 안되기 때문에, 이동할 경로를(a 태그 안에 href) 직접 가져온다. 
		// btns.get(i).click();
		// getAttribute: 요소의 속성값을 가져오는 방법
		// 가져온 경로를 직접 브라우저 주소창(URL)에 넣어준다.
		driver.get(btns.get(i).getAttribute("href"));
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
		// iframe은 브라우저 안에 브라우저
		// 극장 정보는 새로운 iframe에 담겨있기 때문에, 105번으로는 경로를 인식하지 못한다. 
		// 현재 브라우저의 주소창(URL)을 iframe src(경로)로 변경해준다.
		driver.get(driver.findElement(By.id("ticket_iframe")).getAttribute("src"));
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}
		
		// 예매하기 버튼을 누른 후 예매 가능한 극장 수(count) 조회
		for(WebElement webElement : driver.findElements(By.className("count"))) {
			// 가져온 예매 가능한 극장 수를 모두 담아둘 countText
			countText = webElement.getText();
			// 우리가 모르는 count의 0이 있는것 같아 오류를 잡을 수 없다. 
			// 그러므로 continue를 이용해 0이 나오면 건너뛴다.
			if(countText.length() == 0) {continue;}
			// substring 1부터 시작하는 이유? 
			// 극장 수의 화면에 (숫자) 이렇게 나와있다. 숫자부터 자르기 위해 0이 아닌 1부터 시작, countText.length() - 1도 소괄호 전까지
			total += Integer.valueOf(countText.substring(1, countText.length() - 1));
		}
		
		System.out.println("예매 가능한 영화관 수 : " + total);
		
		// 크롬 종료
//		driver.close(); //오류나면 코드 삭제
		driver.quit();
	}
}













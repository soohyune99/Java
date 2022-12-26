package crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Movie {
	
	private WebDriver webDriver;
	private String url;
	public static final String WEB_DRIVER_PATH = "C:/chromedriver_win32/chromedriver.exe";
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	
	public Movie() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		url = "https://www.kobis.or.kr/kobis/business/main/main.do";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		
		webDriver = new ChromeDriver(options);
		webDriver.get(url);
		
	}
	
	public WebDriver searchMovie() {
		Scanner sc = new Scanner(System.in);
		WebElement input  = null, form = null;
		
		List<WebElement> movieTitleElements = null;
		
		ArrayList<String> movieTitles = new ArrayList<String>();
		
		String movieTitle = null;
		
			input =  webDriver.findElement(By.id("inp_solrSearch"));
			
			System.out.println("영화 제목 > ");
			input.sendKeys(sc.nextLine());
			input.sendKeys(Keys.RETURN);
			try {Thread.sleep(1000);} catch (InterruptedException e) {;}
			
			// 전체 게시글
			form = webDriver.findElement(By.className("tbl_comm"));
			// 제목
			movieTitleElements = form.findElements(By.cssSelector("tr td"));
			
			
			for (WebElement webElement : movieTitleElements) {
				movieTitle = webElement.getText();
				movieTitles.add(movieTitle);
			}
			
			for (int i = 0; i < movieTitles.size(); i++) {
				System.out.println(movieTitles.get(i));
			}
			
		
		return webDriver;
	}
	
	public void operate() {
		searchMovie();
		webDriver.quit();
	   }
	
	public static void main(String[] args) {
		new Movie().searchMovie();
	}

}

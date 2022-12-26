package crawling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Melon {
   private WebDriver webDriver;
   private String url;
   public static final String WEB_DRIVER_PATH = "C:/chromedriver_win32/chromedriver.exe";
   public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
   
   public Melon() {
      System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
      url = "https://www.melon.com/";
      ChromeOptions options = new ChromeOptions();
//      options.addArguments("headless");
      
      webDriver = new ChromeDriver(options);
      webDriver.get(url);
      
   }
   
   public WebDriver selectLyric() {
      Scanner sc = new Scanner(System.in);
      WebElement input = null, form = null;
      
      List<WebElement> songNumberElements = null;
      List<WebElement> songTitleElements = null;
      List<WebElement> songArtistElements = null;
      
      ArrayList<String> songNumbers = new ArrayList<String>();
      ArrayList<String> songTitles = new ArrayList<String>();
      ArrayList<String> songAdults = new ArrayList<String>();
      ArrayList<String> songArtists = new ArrayList<String>();
      
      String songNumber = null, songTitle = null, songArtist = null;
      int choice = 0;
      
      try {
         input = webDriver.findElement(By.className("ui-autocomplete-input"));
         
         System.out.print("노래 제목 > ");
         input.sendKeys(sc.nextLine());
         input.sendKeys(Keys.RETURN);
         try {Thread.sleep(1000);} catch (InterruptedException e) {;}
         
         form = webDriver.findElement(By.id("frm_searchSong"));

         
         //번호
         songNumberElements = form.findElements(By.cssSelector("td.no div"));
         //제목
         songTitleElements = form.findElements(By.cssSelector("td.t_left a.fc_gray"));
         
   //      번호 처리
         for (int i = 0; i < songNumberElements.size(); i++) {
            songNumber = songNumberElements.get(i).getText();
            songNumbers.add(songNumber);
            
         }
         
   //      제목 처리
         for (int i = 0; i < songTitleElements.size(); i++) {
            songTitle = songTitleElements.get(i).getText();
            songTitles.add(songTitle);
         }
         
   //      링크 안걸린 제목 처리
         if(songTitleElements.size() != songNumberElements.size()) {
            songTitleElements = form.findElements(By.cssSelector("td.t_left span"));
            songTitles.addAll(songTitleElements.stream().filter(v -> !v.getAttribute("title").equals("")).map(v -> v.getText()).collect(Collectors.toList()));
         }
         
   //      아티스트 처리
         for(WebElement webElement : form.findElements(By.cssSelector("div#artistName"))) {
            songArtistElements = webElement.findElements(By.cssSelector("span a.fc_mgray"));
            songArtist = songArtistElements.size() == 0 ? "없음" : songArtistElements.stream().map(v -> v.getAttribute("title")).map(v -> v.split(" - ")[0]).collect(Collectors.joining(","));
            
            songArtists.add(songArtist);
         }
         
//         19금 처리
         for(WebElement tr : form.findElements(By.cssSelector("tbody tr"))) {
            try {
               songAdults.add(tr.findElement(By.cssSelector("span.age_19")).getText());
            } catch (NoSuchElementException e) {
               songAdults.add("");
            }
         }
         
         for (int i = 0; i < songNumbers.size(); i++) {
            System.out.println(songNumbers.get(i) + " / " + songAdults.get(i) + (songAdults.get(i).equals("") ? "" : "◀ ") + songTitles.get(i) + " / " + songArtists.get(i));
         }
         
         System.out.print("가사를 보고 싶은 곡 번호 : ");
         choice = sc.nextInt() - 1;
         
         form.findElements(By.className("btn_icon_detail")).get(choice).click();
         try {Thread.sleep(1000);} catch (InterruptedException e) {;}
         
         webDriver.findElement(By.className("adult_register"));
         System.out.println("성인 곡의 가사는 열람하실 수 없습니다.");
         
      }catch(NoSuchElementException nsee) {
         try {
            WebElement webElement = webDriver.findElement(By.className("lyric"));
            System.out.println("=========== " + songTitles.get(choice) + " 가사 ===========");
            System.out.println(webElement.getText());
         } catch (NoSuchElementException e) {
            System.out.println("가사가 없는 곡입니다.");
         }
      }
      return webDriver;
   }
   
   public void operate() {
      selectLyric();
      webDriver.quit();
   }
   
   public static void main(String[] args) {
      new Melon().selectLyric().quit();
   }
}











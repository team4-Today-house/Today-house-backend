package com.example.todayhousebackend.crawling;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Crawling1 {

  public static void main(String[] args) throws IOException, SQLException, InterruptedException {


    // 크롬 드라이버 사용
    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    // 경로
    final String WEB_DRIVER_PATH = "C:/Users/유진/Desktop/chromedriver_win32/chromedriver.exe";

    // 드라이버 실행 가능 환경설정
    System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
    // URL 설정
    String url = "https://ohou.se/store?affect_type=Home&affect_id=0";


    // 크롬 옵션 설정
    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.NONE);
    options.addArguments("--remote-allow-origins=*");
    // 크롬 드라이버 객체 생성
    WebDriver driver = new ChromeDriver(options);
    driver.get(url);
    driver.get("https://ohou.se/store?affect_type=Home&affect_id=0"); // 해당 페이지로 이동
    driver.get(url);
    // 브라우저가 완전히 로딩될 때 까지 시간 기다림
    Thread.sleep(150000);

    // 웹 페이지 스크롤
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,1000)");
    Thread.sleep(150000);

    List<WebElement> brandname = driver.findElements(By.cssSelector("#store-index > section.container.store-index-section.store-index-product-list > div.virtualized-list.row > div:nth-child(1) > article > div.production-item__content > h1 > span.production-item__header__brand"));
    List<WebElement> title = driver.findElements(By.cssSelector("#store-index > section.container.store-index-section.store-index-product-list > div.virtualized-list.row > div:nth-child(1) > article > div.production-item__content > h1 > span.production-item__header__name"));
    List<WebElement> discountrate = driver.findElements(By.cssSelector("#store-index > section.container.store-index-section.store-index-product-list > div.virtualized-list.row > div:nth-child(1) > article > div.production-item__content > span.production-item-price > span.production-item-price__rate"));
    List<WebElement> contentsPrice = driver.findElements(By.cssSelector("#store-index > section.container.store-index-section.store-index-product-list > div.virtualized-list.row > div:nth-child(1) > article > div.production-item__content > span.production-item-price > span.production-item-price__price"));

    List<WebElement> productImages = driver.findElements(By.cssSelector("#store-index > section.container.store-index-section.store-index-product-list > div.virtualized-list.row > div:nth-child(1) > article > div.production-item-image.production-item__image"));

    List<Map<String, String>> result = new ArrayList<>();

    for (int i = 0; i < brandname.size(); i++) {
      Element brandnames = (Element) brandname.get(i);
      Element titles = (Element) title.get(i);
      Element discountrates = (Element) discountrate.get(i);
      Element prices = (Element) contentsPrice.get(i);
      WebElement productImage = productImages.get(i);

      String brandnames1 = brandnames.text();
      String title1 = titles.text();
      String discountrates1 = discountrates.text();
      String price = prices.text();
      String imageUrl = productImage.getAttribute("src").split("//?")[0];

      System.out.println("\n브랜드이름: " + brandnames1 + "\n상품명: " + title1 + "\n할인율: " + discountrates1 + "\n가격: " + price + "\n이미지 URL: " + imageUrl);

      Map<String, String> item = new HashMap<>();
      item.put("브랜드 이름", brandnames1);
      item.put("상품명", title1);
      item.put("할인율", discountrates1);
      item.put("가격", price);
      item.put("이미지 URL", imageUrl);

      result.add(item);
    }

    System.out.println(result);

    //driver.quit(); // 브라우저 종료


//    String url = "jdbc:mysql://springboot-database.cwonrvmarhpy.ap-northeast-2.rds.amazonaws.com:3306/todayhouse";
//    String user = "admin";
//    String password = "tjdgur123";
//
//    try(Connection connection = DriverManager.getConnection(url, user, password)){
//      // INSERT 쿼리
//      String query = "INSERT INTO product(brandname, title, discountrate, price) VALUES(?, ?, ?, ?)";
//      PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//      // 데이터 삽입
//      for(Map<String, String> item : result){
//        preparedStatement.setString(1, item.get("브랜드 이름"));
//        preparedStatement.setString(2, item.get("상품명"));
//        preparedStatement.setString(3, item.get("할인율"));
//        preparedStatement.setString(4, item.get("가격"));
//        preparedStatement.executeUpdate();
//      }
//    } catch (SQLException e){
//      e.printStackTrace();
//    }

  }

}
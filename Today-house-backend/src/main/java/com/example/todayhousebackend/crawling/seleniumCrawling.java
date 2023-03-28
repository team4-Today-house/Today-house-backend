package com.example.todayhousebackend.crawling;

import java.awt.AWTException;
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
public class seleniumCrawling {

  public static void main(String[] args) throws InterruptedException, AWTException, IOException {

    WebElement element;

    // 크롬 드라이버 사용
    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    // 경로
    final String WEB_DRIVER_PATH = "D:/사용자 폴더/Administrator/Downloads/chromedriver_win32/chromedriver.exe";

    System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

    // 크롬 옵션 설정
    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.NONE);
    options.addArguments("--remote-allow-origins=*");
    // URL 설정
    String url = "https://ohou.se/store?affect_type=Home&affect_id=0";

    // 크롬 드라이버 객체 생성
    WebDriver driver = new ChromeDriver(options);
    driver.get(url);
    // 브라우저가 완전히 로딩될 때 까지 시간 기다림
    Thread.sleep(4000);

    // 웹 페이지 스크롤
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,40000)");
    Thread.sleep(2000);
    jse.executeScript("window.scrollBy(0,40000)");
    Thread.sleep(2000);
    jse.executeScript("window.scrollBy(0,40000)");
    Thread.sleep(2000);


    List<Map<String, String>> result = new ArrayList<>();
    List<WebElement> productList = driver.findElements(By.xpath("//article[@class='production-item']"));

    int count = 0; // 상품 수를 셀 변수 추가
    // FIXME : 1
    // List dtoList = new ArrayList();
    for (WebElement product : productList) {
      if (count >= 1000) { // 상품 수가 500개 이상이면 크롤링 종료
        break;
      }
      // FIXME : 2
      //Dto dto = new Dto();
      //dto.setBrandname
      //dto.setTitle
      //dto.setDiscountrate
      //dto.setPrice
      //dto.setImg
      String brandname = product.findElement(By.className("production-item__header__brand"))
          .getText();
      String title = product.findElement(By.className("production-item__header__name")).getText();
      String discountrate = product.findElement(By.className("production-item-price__rate"))
          .getText();
      String price = product.findElement(By.className("production-item-price__price")).getText();
      String img = product.findElement(By.xpath("//img[@class='image']")).getAttribute("src");

      count++;

      System.out.println("Brand Name: " + brandname);
      System.out.println("Title: " + title);
      System.out.println("Discount Rate: " + discountrate);
      System.out.println("Price: " + price);
      System.out.println("img = " + img);
      // FIXME : 3
      // dtoList.add(dto)
      Map<String, String> item = new HashMap<>();
      item.put("브랜드 이름", brandname);
      item.put("상품명", title);
      item.put("할인율", discountrate);
      item.put("가격", price);
      item.put("이미지", img);

      result.add(item);
    }

      String url1 = "jdbc:mysql://springboot-database.cwonrvmarhpy.ap-northeast-2.rds.amazonaws.com:3306/todayhouse";
      String user = "admin";
      String password = "tjdgur123";

      try(Connection connection = DriverManager.getConnection(url1, user, password)){
        // INSERT 쿼리
        String query = "INSERT INTO product(brandname, title, discountrate, price, img) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // 데이터 삽입
        for(Map<String, String> item : result){
          preparedStatement.setString(1, item.get("브랜드 이름"));
          preparedStatement.setString(2, item.get("상품명"));
          preparedStatement.setString(3, item.get("할인율"));
          preparedStatement.setString(4, item.get("가격"));
          preparedStatement.setString(5, item.get("이미지"));
          preparedStatement.executeUpdate();
        }
      } catch (SQLException e){
        e.printStackTrace();
      }
    driver.close();
    }

    }



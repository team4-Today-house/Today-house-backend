package com.example.todayhousebackend.crawling;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    final String WEB_DRIVER_PATH = "C:/Users/유진/Desktop/chromedriver_win32/chromedriver.exe";

    // 드라이버 실행 가능 환경설정
    System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

    // 크롬 옵션 설정
    ChromeOptions options = new ChromeOptions();

    // URL 설정
    String url = "https://ohou.se/store?affect_type=Home&affect_id=0";

    // 크롬 드라이버 객체 생성
    WebDriver driver = new ChromeDriver(options);
    driver.get(url);
    // 브라우저가 완전히 로딩될 때 까지 시간 기다림
    Thread.sleep(4000);

    // 웹 페이지 스크롤
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,14000)");
    Thread.sleep(2000);;

    List<WebElement> productList = driver.findElements(By.xpath("//div[@class='production-item__overlay']"));
    for (WebElement product : productList) {
      String brandname = product.findElement(By.className("production-item__header__brand")).getText();
      String title = product.findElement(By.className("production-item__header__name")).getText();
      String discountrate = product.findElement(By.className("production-item-price__rate")).getText();
      String price = product.findElement(By.className("production-item-price__price")).getText();
    }
    driver.quit();
  }
}

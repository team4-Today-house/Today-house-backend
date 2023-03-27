package com.example.todayhousebackend.crawling;
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

import java.util.List;

public class SeleniumJsoupParsingExample {

  public static void main(String[] args) throws InterruptedException {
    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    final String WEB_DRIVER_PATH = "C:/Users/유진/Desktop/chromedriver_win32/chromedriver.exe";
    System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.NONE);
    options.addArguments("--remote-allow-origins=*");
    String url = "https://ohou.se/store?affect_type=Home&affect_id=0";

    WebDriver driver = new ChromeDriver(options);
    driver.get(url);
    Thread.sleep(5000);

    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,4000)");
    Thread.sleep(1000);

    String html = driver.getPageSource();
    Document doc = Jsoup.parse(html);

    // 이미지 링크 추출
    Elements imgList = doc.select("div.production-item-image.production-item__image img.image");
    for (Element img : imgList) {
      String imgSrc = img.attr("src");
      System.out.println("img: " + imgSrc);
    }

    // 상품 정보 추출
    Elements productList = doc.select("div.production-item__content");
    for (Element product : productList) {
      String brandname = product.select("div.production-item__header__brand").text();
      String title = product.select("div.production-item__header__name").text();
      String discountrate = product.select("span.production-item-price__rate").text();
      String price = product.select("span.production-item-price__price").text();

      System.out.println("Brand Name: " + brandname);
      System.out.println("Title: " + title);
      System.out.println("Discount Rate: " + discountrate);
      System.out.println("Price: " + price);
    }

    driver.quit();
  }
}


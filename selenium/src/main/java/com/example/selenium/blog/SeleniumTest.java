package com.example.selenium.blog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class SeleniumTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\사용자 폴더\\Administrator\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100");

        WebElement articleList = driver.findElement(By.id("section_body"));
        for (WebElement article : articleList.findElements(By.tagName("li"))) {
            WebElement titleElement = article.findElement(By.className("cluster_text_headline"));
            String title = titleElement.getText();
            System.out.println(title);
        }

        driver.quit();
    }
}




package com.example.selenium;

import com.example.selenium.blog.Selenium;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class SeleniumApplicationTests {

    @Test
    void contextLoads() {
    }

    @GetMapping("/test")
    public String test() {

        //JsoupFun um = new JsoupFun();

        Selenium sel = new Selenium();

        String url = "https://ohou.se/";

        sel.useDriver(url);



        return "main";
    }

}

package com.example.todayhousebackend.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParsingExample {

  public static void main(String[] args) throws Exception {
    // URL 설정
    String url = "https://ohou.se/store?affect_type=Home&affect_id=0";

    // Jsoup을 사용하여 HTML 문서를 가져옴
    Document doc = Jsoup.connect(url).get();

    // 상품 이미지 가져오기
    for (Element img : doc.select("div.production-item-image.production-item__image img.image")) {
      String imgUrl = img.attr("src");
      System.out.println("img: " + imgUrl);
    }

    // 상품 정보 가져오기
    for (Element product : doc.select("div.production-item__content")) {
      String brandname = product.select("div.production-item__header__brand").text();
      String title = product.select("div.production-item__header__name").text();
      String discountrate = product.select("span.production-item-price__rate").text();
      String price = product.select("span.production-item-price__price").text();

      System.out.println("Brand Name: " + brandname);
      System.out.println("Title: " + title);
      System.out.println("Discount Rate: " + discountrate);
      System.out.println("Price: " + price);
    }
  }
}


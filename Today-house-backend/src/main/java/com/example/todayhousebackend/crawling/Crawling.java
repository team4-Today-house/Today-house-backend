package com.example.todayhousebackend.crawling;


import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

  public static void main(String[] args) throws IOException {
    String homeUrl = "https://ohou.se/store?affect_type=Home&affect_id=0";
    Document doc = Jsoup.connect(homeUrl).get();

    Elements img = doc.getElementsByAttributeValue("class", "//*[@id=\"store-index\"]/section[1]/div/div[1]/div/article/div[1]/div/div/img");

    Elements brandname = doc.getElementsByAttributeValue("class","today-deal-item__header__brand");
    Elements title = doc.getElementsByAttributeValue("class","today-deal-item__header__name");
    Elements contentsName = doc.getElementsByAttributeValue("class","today-deal-item__header__name");
    Elements discountrate = doc.getElementsByAttributeValue("class","production-item-price__rate");
    Elements contentsPrice = doc.getElementsByAttributeValue("class","production-item-price__price");

    for (int i = 0; i < contentsName.size(); i++) {
      Element brandnames = brandname.get(i);
      Element titles = title.get(i);
      Element discountrates = discountrate.get(i);
      Element prices = contentsPrice.get(i);

      String brandnames1 = brandnames.text();
      String title1 = titles.text();
      String discountrates1 = discountrates.text();
      String price = prices.text();
      System.out.println( "\n브랜드이름: " + brandnames1  + "\n상품명: "+ title1 + "\n할인율: " + discountrates1 + "\n가격: " + price);

    }

  }

}
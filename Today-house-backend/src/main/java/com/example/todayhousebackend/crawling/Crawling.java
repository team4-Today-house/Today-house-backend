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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;

public class Crawling {

  public static void main(String[] args) throws IOException, SQLException, InterruptedException {
    String homeUrl = "https://ohou.se/store?affect_type=Home&affect_id=0";
    Document doc = Jsoup.connect(homeUrl).get();

    Elements brandname = doc.getElementsByAttributeValue("class","today-deal-item__header__brand");
    Elements title = doc.getElementsByAttributeValue("class","today-deal-item__header__name");
    Elements discountrate = doc.getElementsByAttributeValue("class","production-item-price__rate");
    Elements contentsPrice = doc.getElementsByAttributeValue("class","production-item-price__price");
    // FIXME : Check
    Elements img = doc.getElementsByAttributeValue("class" , "image");
//    Elements img = doc.select("#store-index > section.container.store-index-section.store-index-today-deal-list > div > div.col-12  > div.today-deal-item--wrapper > article.today-deal-item > div.today-deal-item__image > div.today-deal-item__image__item > div.production-item-image > img");

    List<Map<String, String>> result = new ArrayList<>();
    for (int i = 0; i < title.size(); i++) {
      Element brandnames = brandname.get(i);
      Element titles = title.get(i);
      Element discountrates = discountrate.get(i);
      Element prices = contentsPrice.get(i);
      String imgUrl = img.get(i).attr("src");

      String brandnames1 = brandnames.text();
      String title1 = titles.text();
      String discountrates1 = discountrates.text();
      String price = prices.text();

      System.out.println( "\n브랜드이름: " + brandnames1  + "\n상품명: "+ title1 + "\n할인율: " + discountrates1 + "\n가격: " + price + "\n이미지: " + imgUrl);

      System.out.println("");

      Map<String, String> item = new HashMap<>();
      item.put("브랜드 이름", brandnames1);
      item.put("상품명", title1);
      item.put("할인율", discountrates1);
      item.put("가격", price);
      item.put("이미지", imgUrl);

      result.add(item);
    }
    System.out.println(result);

    String url = "jdbc:mysql://springboot-database.cwonrvmarhpy.ap-northeast-2.rds.amazonaws.com:3306/todayhouse";
    String user = "admin";
    String password = "tjdgur123";

    try(Connection connection = DriverManager.getConnection(url, user, password)){
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

  }

}
package com.example.todayhousebackend.dto;

import com.example.todayhousebackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

  private Long productId;
  private String brandname;
  private String title;
  private String discountrate;
  private String price;

  public ProductResponseDto(Product product) {
    this.productId = product.getProductId();
    this.brandname = product.getBrandname();
    this.title = product.getTitle();
    this.discountrate = product.getDiscountrate();
    this.price = product.getPrice();
  }
}

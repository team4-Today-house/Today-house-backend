package com.example.todayhousebackend.dto;

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
}

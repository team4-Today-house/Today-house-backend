package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.dto.TodayDealResponseDto;
import com.example.todayhousebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/api/hotitem")
  public ResponseEntity<ProductResponseDto> getProductInfo(){
    ProductResponseDto productResponseDto = productService.getProductInfo();
    return ResponseEntity.ok().body(productResponseDto);
  }

  @GetMapping("/api/todayDeal")
  public List<ProductResponseDto> getTodayDeal(){
    return productService.getProduct();
  }

}

package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.HotItemResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
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

  // 상품 조회
  @GetMapping("/api/product")
  public List<ProductResponseDto> getProduct(){return productService.getProduct(); }

  // 핫 아이템 조회
  @GetMapping("/api/hotitem")
  public List<HotItemResponseDto> getTodayDeal(){
    return productService.getHotItem();
  }


}

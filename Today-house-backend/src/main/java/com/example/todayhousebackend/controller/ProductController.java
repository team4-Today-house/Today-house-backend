package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.HotItemResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  // 상품 조회
  @GetMapping("/api/product")
  public List<ProductResponseDto> getProduct(){return productService.getProduct(); }

  // 무한 스크롤
  @GetMapping("/api/products")
  public Page<Product> getProducts(
          @RequestParam("page") int page
  ) {
    // 응답 보내기
    return productService.getProducts(page-1);
  }

  // 핫 아이템 조회
  @GetMapping("/api/hotitem")
  public List<HotItemResponseDto> getTodayDeal(){
    return productService.getHotItem();
  }


}

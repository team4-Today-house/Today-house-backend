package com.example.todayhousebackend.controller;


import com.example.todayhousebackend.dto.HotItemResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DetailPageController {

  private final DetailService detailService;

  @GetMapping("/api/detailPage/product/{productId}")
  public ProductResponseDto getProductProduct(@PathVariable Long productId){
    return detailService.getDetailProductId(productId);
  }

  @GetMapping("/api/detailPage/hotitem/{hotitemId}")
  public HotItemResponseDto getProductHotitem(@PathVariable Long hotitemId){
    return detailService.getDetailHotitemId(hotitemId);
  }


}

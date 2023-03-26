package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.entity.ProductImages;
import com.example.todayhousebackend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CommentService commentService;

  public ProductResponseDto getProductInfo(){
    Optional<Product> product = productRepository.findById(new Product().getProductId());
    if(!product.isPresent()){
      throw new NullPointerException("유효하지 않은 상품입니다");
    }
    // imgSrc 받아오기
//    List<String> imgSrc = new ArrayList<>();
//    for(ProductImages productImages : product.get().getProductImages()){
//      imgSrc.add(productImages.getImgSrc());
//    }

    return ProductResponseDto.builder()
        .productId(product.get().getProductId())
        .brandname(product.get().getBrandname())
        .title(product.get().getTitle())
        .discountrate(product.get().getDiscountrate())
        .price(product.get().getPrice())
        .build();
  }

}

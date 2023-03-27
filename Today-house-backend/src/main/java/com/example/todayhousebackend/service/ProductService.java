package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 오늘의딜 상품 조회기능
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProduct() {
        List<Product> products = productRepository.findAllByOrderByProductId(); // 핫 아이템 조회
        List<ProductResponseDto> productList = new ArrayList<>(); // 오늘의 딜 response객체로 빈 공간 만들기

      for (Product product : products) {
        productList.add(new ProductResponseDto(product));
      }

    return productList;
}

}

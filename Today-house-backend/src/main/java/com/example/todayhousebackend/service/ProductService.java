package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.HotItemResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.repository.HotItemRepository;
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
  private final HotItemRepository hotItemRepository;

    // 상품 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProduct() {

        List<Product> products = productRepository.findAllByOrderByProductId(); // 상품 조회
        List<ProductResponseDto> productList = new ArrayList<>(); // 상품 response객체로 빈 공간 만들기

      for (Product product : products) {
        productList.add(new ProductResponseDto(product));
      }

    return productList;
}

  @Transactional(readOnly = true)
  public List<HotItemResponseDto> getHotItem() {

    List<HotItem> hotItems = hotItemRepository.findAllByOrderByHotitemId();
    List<HotItemResponseDto> hotItemList = new ArrayList<>();

    for (HotItem hotItem : hotItems) {
      hotItemList.add(new HotItemResponseDto(hotItem));
    }

    return hotItemList;
  }

}

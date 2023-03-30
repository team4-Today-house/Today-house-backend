package com.example.todayhousebackend.service;


import com.example.todayhousebackend.dto.HotItemResponseDto;
import com.example.todayhousebackend.dto.ProductResponseDto;
import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.exception.ApiException;
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.repository.HotItemRepository;
import com.example.todayhousebackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetailService {

  private final ProductRepository productRepository;
  private final HotItemRepository hotItemRepository;

  @Transactional(readOnly = true)
  public ProductResponseDto getDetailProductId(Long id){

    Product product = productRepository.findById(id).orElseThrow(
        () -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT)
    );

    return  new ProductResponseDto(product);
  }

  @Transactional(readOnly = true)
  public HotItemResponseDto getDetailHotitemId(Long id){
    HotItem hotItem = hotItemRepository.findById(id).orElseThrow(
        () -> new ApiException(ExceptionEnum.NOT_FOUND_PRODUCT)
    );

    return  new HotItemResponseDto(hotItem);
  }
}

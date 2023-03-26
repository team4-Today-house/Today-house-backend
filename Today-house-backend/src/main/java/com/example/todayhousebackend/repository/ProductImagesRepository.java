package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

  ProductImages findByProduct(Product product);


}

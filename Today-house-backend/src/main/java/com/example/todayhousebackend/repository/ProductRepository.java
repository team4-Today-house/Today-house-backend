package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

//  Product findAllByProductIdNotIn(List<Long> productList);

  Page<Product> findAll(Pageable pageable);

  List<Product> findAllByOrderByProductId();

}

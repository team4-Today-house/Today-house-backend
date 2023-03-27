package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findAllByProductIdNotIn(List<Long> productList);

  List<Product> findAllByOrderByProductId();

}

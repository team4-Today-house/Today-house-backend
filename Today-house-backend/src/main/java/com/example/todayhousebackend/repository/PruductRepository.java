package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PruductRepository extends JpaRepository<Product, Long> {
}

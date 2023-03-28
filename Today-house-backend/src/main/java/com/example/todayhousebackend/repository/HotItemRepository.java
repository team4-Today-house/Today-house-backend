package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotItemRepository extends JpaRepository<HotItem, Long> {
    List<HotItem> findAllByOrderByHotitemId();
}

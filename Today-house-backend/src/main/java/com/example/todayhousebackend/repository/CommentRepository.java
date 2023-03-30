package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByProduct(Product product); // 상품 기준으로 댓글을 찾는다. Product 객체가 들어와야함

}

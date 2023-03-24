package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

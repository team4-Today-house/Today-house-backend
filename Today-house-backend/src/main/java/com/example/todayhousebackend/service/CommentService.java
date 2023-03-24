package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.repository.CommentRepository;
import com.example.todayhousebackend.repository.PruductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PruductRepository pruductRepository; // Repository는 Db 로직 수행, sql 구문 수행

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Product product = pruductRepository.findById(commentRequestDto.getProductId()).orElseThrow( // product 아이디가 존재하지 않으면 제품이 없다는 뜻이므로,
                () -> new IllegalArgumentException("제품이 존재하지 않습니다!") // 예외처리를 써준다.
        );

        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto, user, product));

        return new CommentResponseDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> getComments(Long productId){
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findAll();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }
}
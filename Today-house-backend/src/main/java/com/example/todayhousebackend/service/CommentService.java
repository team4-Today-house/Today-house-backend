package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.repository.CommentRepository;
import com.example.todayhousebackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository; // Repository는 Db 로직 수행, sql 구문 수행

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Product product = productRepository.findById(commentRequestDto.getProductId()).orElseThrow( // product 아이디가 존재하지 않으면 제품이 없다는 뜻이므로,
                () -> new IllegalArgumentException("제품이 존재하지 않습니다!") // 예외처리를 써준다.
        );

        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto, user, product));

        return new CommentResponseDto(comment, user);
    }

    @Transactional
    public List<CommentResponseDto> getComments(){
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findAll();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment, new User()));
        }
        return commentResponseDtoList;
    }

    // 수정
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto , User user) {

        Comment comment = checkComment(commentId);

        comment.update(commentRequestDto);

        return new CommentResponseDto(comment, user);
    }

    // 삭제
    public void deleteComment(Long commentId, User user) {
        checkComment(commentId);

        commentRepository.deleteById(commentId);
    }

    // 상품 존재 여부
    public Product checkProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    // 댓글 존재 여부
    public Comment checkComment (Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }


}
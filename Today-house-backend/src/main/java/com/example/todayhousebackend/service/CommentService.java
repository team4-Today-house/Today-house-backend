package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.entity.Product;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.exception.ApiException;
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.repository.CommentRepository;
import com.example.todayhousebackend.repository.ProductRepository;
import com.example.todayhousebackend.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto createComment(Long productId, CommentRequestDto commentRequestDto, User user) {

        Product product = checkProduct(productId);

        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto, user, product));

        return new CommentResponseDto(comment);
    }

    // 상품 상세 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long productId){

        Product product = checkProduct(productId);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : product.getComments()) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }

        return commentResponseDtoList;
    }

    // 수정 : 회원 체크 후 수정을 해야한다.
    @Transactional
    public CommentResponseDto updateComment(Long productId, Long commentId, CommentRequestDto commentRequestDto, User user) {

        checkProduct(productId);

        userInfo(user.getUserId());

        Comment comment = checkComment(commentId);

        comment.update(commentRequestDto);

        return new CommentResponseDto(comment);
    }

    // 삭제 : 회원 체크 후 삭제를 해야한다.
    @Transactional
    public void deleteComment(Long productId, Long commentId, User user) {

        checkProduct(productId);

        userInfo(user.getUserId());

        checkComment(commentId);

        commentRepository.deleteById(commentId);
    }
    // 회원 존재 여부
    public User userInfo(Long userId) {
        return userRepository.findById(userId).orElseThrow( () -> new IllegalArgumentException("회원이 아닙니다."));
    }

    // 상품 존재 여부
    public Product checkProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    // 댓글 존재 여부
    public Comment checkComment (Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ADMIN));
    }


}
package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.security.UserDetailsImpl;
import com.example.todayhousebackend.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/detailPage/{productId}/comment")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(commentRequestDto, userDetails.getUser());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("msg", "작성성공");
        responseBody.put("data", commentService.createComment(commentRequestDto, userDetails.getUser()));

        return ResponseEntity.status(HttpStatus.CREATED)
              .body(responseBody);
    }

    // 상품 댓글 조회
    @GetMapping("/detailPage/{productId}/comment")
    public List<CommentResponseDto> getComments(@PathVariable Long productId){
        return commentService.getComments(productId);
    }

    @PatchMapping("/detailPage/{productId}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long productId,@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(productId, commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/detailPage/{productId}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long productId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(productId, commentId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글삭제완료");
    }

}

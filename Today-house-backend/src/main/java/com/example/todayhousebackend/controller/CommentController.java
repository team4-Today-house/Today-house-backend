package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.security.UserDetailsImpl;
import com.example.todayhousebackend.service.CommentService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/detailPage/{productId}/comment", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> createComment(@PathVariable Long productId, @RequestParam(value = "image") MultipartFile image, Comment comment, @RequestPart CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
        throws IOException {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("msg", "작성성공");
        responseBody.put("data", commentService.createComment(productId, image, comment, commentRequestDto, userDetails.getUser()));

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

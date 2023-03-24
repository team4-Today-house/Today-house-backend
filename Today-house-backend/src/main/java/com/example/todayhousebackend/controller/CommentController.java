package com.example.todayhousebackend.controller;

import com.example.todayhousebackend.dto.CommentRequestDto;
import com.example.todayhousebackend.dto.CommentResponseDto;
import com.example.todayhousebackend.security.UserDetailsImpl;
import com.example.todayhousebackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return  commentService.createComment(commentRequestDto, userDetails.getUser());
    }

    @GetMapping("/api/comment/{commentId}")
    public List<CommentResponseDto> getComments(@PathVariable Long commentId){
        return commentService.getComments(commentId);
    }
}

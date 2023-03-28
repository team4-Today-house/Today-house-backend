package com.example.todayhousebackend.dto;

import com.example.todayhousebackend.entity.Comment;
import com.example.todayhousebackend.entity.User;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long commentId;
    private String comment;
    private int star;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String loginId;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.star = comment.getStar();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.loginId = comment.getUser().getLoginId();
    }
}




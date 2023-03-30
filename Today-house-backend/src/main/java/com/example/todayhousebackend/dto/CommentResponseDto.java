package com.example.todayhousebackend.dto;

import com.example.todayhousebackend.entity.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;


@Getter
public class CommentResponseDto {

    private Long commentId;
    private String comment;
    private int star;
    private String createdAt;
    private String modifiedAt;
    private String loginId;
    private String imgUrl;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.star = comment.getStar();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
        this.modifiedAt = comment.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a h:mm"));
        this.loginId = comment.getUser().getLoginId();
    }

    public CommentResponseDto(Comment comment, String imgUrl){
        this(comment);
        this.imgUrl = imgUrl;
    }
}




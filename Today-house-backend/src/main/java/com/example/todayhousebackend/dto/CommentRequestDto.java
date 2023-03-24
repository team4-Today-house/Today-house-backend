package com.example.todayhousebackend.dto;

import lombok.Getter;


@Getter
public class CommentRequestDto {

    private Long productId;
    private int star;
    private String contents;
}

package com.example.todayhousebackend.dto;

import lombok.Getter;


@Getter
public class CommentRequestDto {

    private int star;
    private String contents;
    private String imgUrl;
}

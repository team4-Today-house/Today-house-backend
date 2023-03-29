package com.example.todayhousebackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

  INVAILD_TOKEN(HttpStatus.BAD_REQUEST, 400,"토큰이 유효하지 않습니다."),
  NOT_TOKEN(HttpStatus.BAD_REQUEST, 400,"로그인 해주세요.(토큰없음)"),

  ONLY_ADMIN(HttpStatus.BAD_REQUEST, 400,"관리자계정으로 삭제가능합니다."),
  DUPLICATE_USER(HttpStatus.BAD_REQUEST, 400,"중복된 사용자입니다."),

  NOT_FOUND_PASSWORD(HttpStatus.BAD_REQUEST, 400,"인증정보가 일치하지 않습니다."),
  NOT_FOUND_USER(HttpStatus.BAD_REQUEST, 400,"회원을 찾을 수 없습니다."),
  NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST,404, "상품이 없습니다."),
  NOT_FOUND_POST(HttpStatus.BAD_REQUEST, 403,"게시글 수정 권한이 없습니다."),
  NOT_FOUND_POST_ADMIN(HttpStatus.BAD_REQUEST, 404,"게시물을 찾을 수 없습니다."),
  NOT_FOUND_COMMENT(HttpStatus.BAD_REQUEST, 403,"댓글 수정 권한이 없습니다."),
  NOT_FOUND_COMMENT_ADMIN(HttpStatus.NOT_FOUND, 404,"댓글을 찾을 수 없습니다."),
  ;

  private HttpStatus status;
  private int statusCode;
  private String message;
}

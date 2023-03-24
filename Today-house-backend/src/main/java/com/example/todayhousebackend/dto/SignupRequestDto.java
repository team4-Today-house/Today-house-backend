package com.example.todayhousebackend.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
  @NotBlank(message = "로그인 아이디를 입력해주세요.")
  @Size(min = 6, max = 16)
  @Pattern(regexp = "[a-z0-9]", message = "영어 소문자와 숫자로만 구성해주세요.")
  private String loginId;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 8, max = 10)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,15}$", message = "숫자와 영어 소문자와 특수문자를 사용해 8-15자리 비밀번호를 입력해주세요.")
  private String password;

  @NotBlank(message = "이메일을 입력해주세요.")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
  private String email;


}

//`아이디`  6자 ~ 16자 / 소문자와 숫자
//
//`이름` 2~10자 / 한글, 영어만
//
//`비밀번호` 8~15자 / 영문자 1개, 숫자 1개, 특수문자 1개 이상
//
//`이메일` 이메일 형식

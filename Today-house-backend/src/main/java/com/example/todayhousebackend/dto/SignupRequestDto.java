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
  @Pattern(regexp = "^*[a-z0-9]{6,16}$",
          message = "아이디는 소문자와 숫자 포함된 6자 ~ 16자의 아이디여야 합니다.")
  private String loginId;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Pattern(regexp = "^*(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,15}$", message = "8~15글자, 영문자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
  private String password;

  @NotBlank(message = "이메일을 입력해주세요.")
  @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식에 맞지 않습니다.")
  private String email;


}



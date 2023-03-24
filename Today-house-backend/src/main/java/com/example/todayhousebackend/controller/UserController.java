package com.example.todayhousebackend.controller;


import com.example.todayhousebackend.dto.SignupRequestDto;
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.jwt.JwtUtil;
import com.example.todayhousebackend.service.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/user/signup")
  public ResponseEntity<ExceptionEnum> signup(@RequestBody @Valid SignupRequestDto dto){

    userService.signup(dto);

    return new ResponseEntity("회원가입 성공", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody SignupRequestDto dto, HttpServletResponse response){
    String token = userService.login(dto);

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    return new ResponseEntity("로그인 성공", HttpStatus.OK);
  }


}

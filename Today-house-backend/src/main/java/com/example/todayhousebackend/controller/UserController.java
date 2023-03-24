package com.example.todayhousebackend.controller;


import com.example.todayhousebackend.dto.SignupRequestDto;
<<<<<<< HEAD
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.exception.RestApiException;
import com.example.todayhousebackend.exception.RestApiExceptionHandler;
=======
>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
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
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

<<<<<<< HEAD
  @PostMapping("/api/user/signup")
  public ResponseEntity<ExceptionEnum> signup(@RequestBody @Valid SignupRequestDto dto){
=======
  @PostMapping("/signup")
  public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequestDto dto){

>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
    userService.signup(dto);

    return ResponseEntity.ok(ExceptionEnum.INVAILD_TOKEN);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody SignupRequestDto dto, HttpServletResponse response){
    String token = userService.login(dto);
<<<<<<< HEAD
    resp.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
=======
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
    return new ResponseEntity("로그인 성공", HttpStatus.OK);
  }


}

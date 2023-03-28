package com.example.todayhousebackend.controller;


import com.example.todayhousebackend.dto.SignupRequestDto;
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.jwt.JwtUtil;
import com.example.todayhousebackend.service.KakaoService;
import com.example.todayhousebackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final KakaoService kakaoService;

  @PostMapping("/api/user/signup")
  public ResponseEntity<Map<String, String>> signup(@RequestBody @Valid SignupRequestDto dto){

    userService.signup(dto);
    Map<String, String> response = new HashMap<>();
    response.put("회원가입 성공", String.valueOf(HttpStatus.OK));

    return ResponseEntity.ok(response);
  }

  @PostMapping("/api/user/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody SignupRequestDto dto, HttpServletResponse response){
    String token = userService.login(dto);
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    Map<String, String> res = new HashMap<>();
    res.put("로그인 성공", String.valueOf(HttpStatus.OK));
    return ResponseEntity.ok(res);
  }

  @GetMapping ("/kakao/callback")
  public ResponseEntity kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
    // code: 카카오 서버로부터 받은 인가 코드
    String createToken = kakaoService.kakaoLogin(code, response);

    // Cookie 생성 및 직접 브라우저에 Set
    Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
    cookie.setPath("/");
    response.addCookie(cookie); // addCookie 를 사용해 이 안에 cookie를 서버에서 넣어줄 수 있다.
    // 클라이언트에서 직접 쿠키를 받아서 쿠키 저장소에 저장하지 않아도 자동으로 쿠키 저장소에 저장.

    return ResponseEntity.ok().body("카카오로그인");
  }

}

package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.SignupRequestDto;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.exception.ApiException;
import com.example.todayhousebackend.exception.ExceptionEnum;
import com.example.todayhousebackend.jwt.JwtUtil;
import com.example.todayhousebackend.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;



  @Transactional
  public void signup(SignupRequestDto dto){

    Optional<User> found = userRepository.findByLoginId(dto.getLoginId());
    if(found.isPresent()) {
      throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
    }

    String password = passwordEncoder.encode(dto.getPassword());

    User user = new User(dto ,password);
    userRepository.save(user);
  }

  @Transactional
  public String login(SignupRequestDto dto){
    String loginId = dto.getLoginId();

    User user = userRepository.findByLoginId(loginId).orElseThrow(
        () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
    );

    String encodePassword = user.getPassword();

    if(!passwordEncoder.matches(dto.getPassword(), encodePassword)){
      throw new IllegalArgumentException("인증 정보가 맞지 않습니다.");
    }
    return jwtUtil.createToken(user.getLoginId());
  }

}

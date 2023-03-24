package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.SignupRequestDto;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.jwt.JwtUtil;
import com.example.todayhousebackend.repository.UserRepository;
import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;

  @Transactional
  public void signup(SignupRequestDto dto){
    String loginId = dto.getLoginId();
    String password = encoder.encode(dto.getPassword());
    String email = dto.getEmail();

    Optional<User> found = userRepository.findByLoginId(loginId);

    if(found.isPresent()) {
      throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
    }

    User user = new User(loginId, password, email);
    userRepository.save(user);
  }

  @Transactional
  public String login(SignupRequestDto dto){
    String loginId = dto.getLoginId();

    User user = userRepository.findByLoginId(loginId).orElseThrow(
        () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
    );

    String encodePassword = user.getPassword();

    if(!encoder.matches(dto.getPassword(), encodePassword)){
      throw new IllegalArgumentException("인증 정보가 맞지 않습니다.");
    }
    return jwtUtil.createToken(user.getLoginId());
  }

}

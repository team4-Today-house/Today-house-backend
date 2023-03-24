package com.example.todayhousebackend.service;

import com.example.todayhousebackend.dto.SignupRequestDto;
import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.entity.UserRoleEnum;
import com.example.todayhousebackend.jwt.JwtUtil;
import com.example.todayhousebackend.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private static final String ADMIN_TOKEN = "admin";

  @Transactional
  public void signup(SignupRequestDto dto){
//    String password = encoder.encode(dto.getPassword());

    Optional<User> found = userRepository.findByLoginId(dto.getLoginId());
    if(found.isPresent()) {
      throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
    }
    UserRoleEnum role = UserRoleEnum.USER;

<<<<<<< HEAD
    User user = new User(loginId, password, email, role);
    userRepository.saveAndFlush(user);
=======
    String password = passwordEncoder.encode(dto.getPassword());


    User user = new User(dto ,password);
    userRepository.save(user);
>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
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
    return jwtUtil.createToken(user.getLoginId(), user.getRole());
  }

}

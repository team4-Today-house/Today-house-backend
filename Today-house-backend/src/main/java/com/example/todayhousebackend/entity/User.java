package com.example.todayhousebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.todayhousebackend.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  private String loginId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  private Long kakaoId;


  public User(SignupRequestDto dto, String password) {
    this.loginId = dto.getLoginId();
    this.password = password;
    this.email = dto.getEmail();
  }

  public User(String loginId, Long kakaoId, String password, String email) {
    this.loginId = loginId;
    this.kakaoId = kakaoId;
    this.password = password;
    this.email = email;
  }

  public User kakaoIdUpdate(Long kakaoId) {
    this.kakaoId = kakaoId;
    return this;
  }
}

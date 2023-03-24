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

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;


<<<<<<< HEAD
  public User(String loginId, String password, String email, UserRoleEnum role) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
    this.role = role;
=======
  public User(SignupRequestDto dto, String password) {
    this.loginId = dto.getLoginId();
    this.password = password;
    this.email = dto.getEmail();
>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
  }
}

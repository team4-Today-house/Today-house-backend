package com.example.todayhousebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column(nullable = false)
  private String loginId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;


  public User(String loginId, String password, String email) {
    this.loginId = loginId;
    this.password = password;
    this.email = email;
  }
}

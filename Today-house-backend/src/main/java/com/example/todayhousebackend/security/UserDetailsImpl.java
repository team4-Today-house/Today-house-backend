package com.example.todayhousebackend.security;

import com.example.todayhousebackend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

  private final User user;
  private final String loginId;

  public UserDetailsImpl(User user, String loginId) {
    this.user = user;
    this.loginId = loginId;
  }

  public User getUser() {
    return user;
  }

//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    UserRoleEnum role = user.getRole();
//    String authority = role.getAuthority();
//
//    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
//    Collection<GrantedAuthority> authorities = new ArrayList<>();
//    authorities.add(simpleGrantedAuthority);
//
//    return authorities;
//  }

  @Override
  public String getUsername() {
    return this.loginId;
  }


  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
package com.example.todayhousebackend.security;

import com.example.todayhousebackend.entity.User;
import com.example.todayhousebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    User user = userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

<<<<<<< HEAD
    return new UserDetailsImpl(user, user.getLoginId());
=======
    return new UserDetailsImpl(user);
>>>>>>> 4555c85a7da712cfaa238f9e7d59bf84b1421ed4
  }
}
package com.example.todayhousebackend.repository;

import com.example.todayhousebackend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLoginId(String loginId);

  Optional<User> findByKakaoId(Long id);

  Optional<User> findByEmail(String email);

}

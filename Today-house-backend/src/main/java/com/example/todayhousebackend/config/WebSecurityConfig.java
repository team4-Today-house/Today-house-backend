package com.example.todayhousebackend.config;

import com.example.todayhousebackend.jwt.JwtAuthFilter;
import com.example.todayhousebackend.jwt.JwtUtil;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  @Profile("db-local")
  public WebSecurityCustomizer localWebSecurityCustomizer() {

    return (web) -> web.ignoring()
        //.requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  @Profile("db-dev")
  public WebSecurityCustomizer devWebSecurityCustomizer() {

    return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
        .antMatchers("/**").permitAll()
        .antMatchers(HttpMethod.GET,"/api/**").permitAll()
        .anyRequest().authenticated()
        .and().cors()

        // JWT 인증/인가를 사용하기 위한 설정
        .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

    http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource(){

    CorsConfiguration config = new CorsConfiguration();

    // 사전에 약속된 출처를 명시
    config.addAllowedOrigin("http://localhost:3001");
    config.addAllowedOrigin("http://localhost:3000");
//    config.addAllowedOrigin("http://mini-prject-team3.s3-website.ap-northeast-2.amazonaws.com");
//    config.addAllowedOrigin("http://charleybucket.s3-website.ap-northeast-2.amazonaws.com");

    config.setAllowedOriginPatterns(Arrays.asList("*"));

    config.addAllowedOrigin("https://team8-front.vercel.app");

    config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);

    config.addAllowedMethod("*");

    config.addAllowedHeader("*");

    config.setAllowCredentials(true);


    config.validateAllowCredentials();

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return source;
  }

}
package com.in28minutes.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.config.AuthConfig;

@Configuration
public class SpringSecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 모든 요청이 인증된 사용자만이 볼 수 있다
//		http.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated()
//				);
		http.authorizeHttpRequests().anyRequest().authenticated();
		// 인증 방식을 기본으로 있는걸 사용(팝업창)
		http.httpBasic(withDefaults());
		// put post요청이 csrf인증없이 이루어지도록 (임시)
		http.csrf().disable();

		return http.build();
	}
}

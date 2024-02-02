package com.nkedu.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf(CsrfConfigurer::disable)
				.exceptionHandling(authenticationManager -> authenticationManager
						.authenticationEntryPoint(jwtAuthenticationEntryPoint)
						.accessDeniedHandler(jwtAccessDeniedHandler))
				
				.sessionManagement(configurer -> configurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				// 모든 HttpServletRequest 에 접근 제한을 걸어둠
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/parent/signup").permitAll()
						.requestMatchers("/api/signup").permitAll()
						.requestMatchers("/api/authenticate").permitAll()
						
						.requestMatchers("/parent/*").permitAll()
						.requestMatchers("/student/*").permitAll()
						
						.requestMatchers("/favicon.ico").permitAll()
						.anyRequest().authenticated() // 그 외 인증 없이 접근 불가능
				 );
				
		return httpSecurity.build();
	}
}

package com.nkedu.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.dto.LoginDTO;
import com.nkedu.back.dto.RefreshTokenDTO;
import com.nkedu.back.dto.TokenDTO;
import com.nkedu.back.security.TokenProvider;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 별도의 Service 로의 연결은 없고 Spring Security 의 로그인 과정을 처리해주는 Controller 코드입니다.
     * 
     * @author DevTae
     */
    
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        
        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = null;
        try {
        	authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (UsernameNotFoundException e) {
        	logger.debug("login failed. " + loginDTO.getUsername());
        	return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // authentication 정보를 바탕으로 refresh token을 생성
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        // refresh token 을 바탕으로 access token 생성
        String accessToken = tokenProvider.createAccessToken(refreshToken);

        return new ResponseEntity<>(new TokenDTO(refreshToken, accessToken), HttpStatus.OK);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        
    	String accessToken = tokenProvider.createAccessToken(refreshTokenDTO.getRefreshToken());
    	
    	if (accessToken == null) {
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);    		
    	}
    	
    	return new ResponseEntity<>(new TokenDTO(refreshTokenDTO.getRefreshToken(), accessToken), HttpStatus.OK);
    }
    
    // 로그인 체크
    @GetMapping("/login_check")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> login_check_user() {
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    // 로그인 체크 (부모님)
    @GetMapping("/login_check_parent")
    @PreAuthorize("hasRole('ROLE_PARENT')")
    public ResponseEntity<Void> login_check_parent() {
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    // 로그인 체크 (부모님)
    @GetMapping("/login_check_student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Void> login_check_student() {
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    // 로그인 체크 (선생님)
    @GetMapping("/login_check_teacher")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<Void> login_check_teacher() {
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    // 로그인 체크 (관리자)
    @GetMapping("/login_check_admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> login_check_admin() {
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}

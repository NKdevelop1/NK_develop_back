package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkedu.back.api.UserService;
import com.nkedu.back.model.Authority;
import com.nkedu.back.model.Parent;
import com.nkedu.back.model.ParentDto;
import com.nkedu.back.model.User;
import com.nkedu.back.model.UserDto;
import com.nkedu.back.repository.UserRepository;
import com.nkedu.back.security.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public boolean signup(UserDto userDto) {
        try{

	        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
	            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
	        }
	
	        System.out.println(userDto.getUsername());
	        // 가입되어 있지 않은 회원이면,
	        // 권한 정보 만들고
	        Authority authority = Authority.builder()
	                .authorityName("ROLE_USER")
	                .build();
	
	        // 유저 정보를 만들어서 save
	        User user = User.builder()
	                .username(userDto.getUsername())
	                .password(passwordEncoder.encode(userDto.getPassword()))
	                .nickname(userDto.getNickname())
	                .authorities(Collections.singleton(authority))
	                .activated(true)
	                .build();
	
	        userRepository.save(user);
	        return true;
        } catch(Exception e) {
        	log.error("Failed: " + e.getMessage(),e);
        }
        return false;
    
    }
    
    // 부모 전용 signup
    @Transactional
    public boolean signup(ParentDto parentDto) {
        try{
	        if (ObjectUtils.isNotEmpty(userRepository.findOneByUsername(parentDto.getUsername()))) {
	            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
	        }
	
	        System.out.println("getUserName: " + parentDto.getUsername());
	        
	        // 가입되어 있지 않은 회원이면,
	        // 권한 정보 만들고
	        Authority authority = Authority.builder()
	                .authorityName("ROLE_USER")
	                .build();
	        
	        System.out.println("authority: " + authority.toString());
	        System.out.println("authority_name: " + authority.getAuthorityName());
	
	        // 유저 정보를 만들어서 save
	        Parent parent = (Parent) Parent.builder()
	                .username(parentDto.getUsername())
	                .password(passwordEncoder.encode(parentDto.getPassword()))
	                .nickname(parentDto.getNickname())
	                .birth(parentDto.getBirth())
	                .phoneNumber(parentDto.getPhoneNumber())
	                .authorities(Collections.singleton(authority))
	                .created(new Timestamp(System.currentTimeMillis()))
	                .activated(true)
	                .build();
	
	        userRepository.save(parent);
	        return true;
        } catch(Exception e) {
        	log.error("Failed: " + e.getMessage(),e);
        }
        return false;
    
    }

    // 유저,권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
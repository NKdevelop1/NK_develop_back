package com.nkedu.back.serviceImpl;

import java.util.Collections;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkedu.back.api.UserService;
import com.nkedu.back.entity.Authority;
import com.nkedu.back.entity.User;
import com.nkedu.back.dto.UserDto;
import com.nkedu.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // user 에 대한 signup 은 deprecated 될 예정.
    @Transactional
    public boolean signup(UserDto userDto) {
        try{
	        if (ObjectUtils.isNotEmpty(userRepository.findOneByUsername(userDto.getUsername()).get())) {
	            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
	        }

	        System.out.println(userDto.getUsername());
	        
	        Authority authority = Authority.builder()
	                .authorityName("ROLE_USER")
	                .build();

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
    

    /*
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
    */
}
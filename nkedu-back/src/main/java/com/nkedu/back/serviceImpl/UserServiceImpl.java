package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkedu.back.api.UserService;
import com.nkedu.back.entity.Authority;
import com.nkedu.back.entity.Parent;
import com.nkedu.back.dto.ParentDto;
import com.nkedu.back.entity.User;
import com.nkedu.back.dto.UserDto;
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

    @Transactional
    public boolean signup(ParentDto parentDto) {
        try{
	        if (ObjectUtils.isNotEmpty(userRepository.findOneByUsername(parentDto.getUsername()))) {
	            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
	        }
	
	        System.out.println("getUserName: " + parentDto.getUsername());
	        
	        Set<Authority> authorities = new HashSet<Authority>();
	        
	        Authority authority_user = Authority.builder()
	                .authorityName("ROLE_USER")
	                .build();
	        authorities.add(authority_user);
	        
	        Authority authority_parent = Authority.builder()
	        		.authorityName("ROLE_PARENT")
	        		.build();
	        authorities.add(authority_parent);
	
	        Parent parent = (Parent) Parent.builder()
	                .username(parentDto.getUsername())
	                .password(passwordEncoder.encode(parentDto.getPassword()))
	                .nickname(parentDto.getNickname())
	                .birth(parentDto.getBirth())
	                .phoneNumber(parentDto.getPhoneNumber())
	                .authorities(authorities)
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

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
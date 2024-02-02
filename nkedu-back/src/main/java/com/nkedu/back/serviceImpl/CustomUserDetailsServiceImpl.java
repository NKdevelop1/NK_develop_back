package com.nkedu.back.serviceImpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nkedu.back.model.User;
import com.nkedu.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
    private final UserRepository userRepository;

    @Override
    @Transactional
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(final String username) {
    	System.out.println("loadUserByUsername");
    	
    	User user_tmp = userRepository.findOneByUsername(username).get();
    	System.out.println("user.getAuthorities(): " + user_tmp.toString() + user_tmp.getAuthorities());

    	// Exception 부분은 공식문서 참고하여 수정할 예정입니다. (loadUserByUsername 함수)
    	UserDetails userDetails = userRepository.findOneByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    	
    	System.out.println("userDetails: " + userDetails.toString());
    	
    	return userDetails;
    	
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
    	
    	System.out.println("user: " + user.toString());
    	
        if (!user.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        
        System.out.println("grantedAuthorities.get(0).getAuthority : " + grantedAuthorities.get(0).getAuthority());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
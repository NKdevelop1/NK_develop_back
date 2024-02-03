package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.nkedu.back.api.ParentService;
import com.nkedu.back.entity.Authority;
import com.nkedu.back.entity.Parent;
import com.nkedu.back.dto.ParentDto;
import com.nkedu.back.repository.ParentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

	private final ParentRepository parentRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean createParent(ParentDto parentDto) {
		try{
	        if (!ObjectUtils.isEmpty(parentRepository.findOneByUsername(parentDto.getUsername()))) {
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
	
	        parentRepository.save(parent);
	        return true;
        } catch(Exception e) {
        	log.error("Failed: " + e.getMessage(),e);
        }
        return false;
	}

	@Override
	public boolean deleteByUsername(String username) {
		try {
			parentRepository.delete(parentRepository.findOneByUsername(username).get());
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateParent(String username, ParentDto parentDto) {
		try {
			Parent searchedParent = parentRepository.findOneByUsername(username).get();
			
			if(ObjectUtils.isEmpty(searchedParent))
				return false;
			
			if(!ObjectUtils.isEmpty(parentDto.getUsername()))
				searchedParent.setUsername(parentDto.getUsername());
			if(!ObjectUtils.isEmpty(parentDto.getPassword()))
				searchedParent.setPassword(parentDto.getPassword());
			if(!ObjectUtils.isEmpty(parentDto.getNickname()))
				searchedParent.setNickname(parentDto.getNickname());
			if(!ObjectUtils.isEmpty(parentDto.getPhoneNumber()))
				searchedParent.setPhoneNumber(parentDto.getPhoneNumber());	
			if(!ObjectUtils.isEmpty(parentDto.getBirth()))
				searchedParent.setBirth(parentDto.getBirth());
			
			parentRepository.save(searchedParent);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return false;
	}

	@Override
	public List<ParentDto> getParents() {
		try {
			List<ParentDto> parentDtos = new ArrayList<>();
			
			List<Parent> parents = parentRepository.findAll();
			
			for(Parent parent : parents) {
				ParentDto parentDto = new ParentDto();
				parentDto.setId(parent.getId());
				parentDto.setUsername(parent.getUsername());
				parentDto.setNickname(parent.getNickname());
				parentDto.setPhoneNumber(parent.getPhoneNumber());
				parentDto.setBirth(parent.getBirth());
				
				parentDtos.add(parentDto);
			}
			
			return parentDtos;
		} catch(Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

	@Override
	public ParentDto findByUsername(String username) {
		
		try {
			Parent parent = parentRepository.findOneByUsername(username).get();
			
			ParentDto parentDto = new ParentDto();
			parentDto.setId(parent.getId());
			parentDto.setUsername(parent.getUsername());
			parentDto.setNickname(parent.getNickname());
			parentDto.setPhoneNumber(parent.getPhoneNumber());
			parentDto.setBirth(parent.getBirth());
			
			return parentDto;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

}

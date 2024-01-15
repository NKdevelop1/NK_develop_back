package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.nkedu.back.api.ParentService;
import com.nkedu.back.model.Parent;
import com.nkedu.back.model.ParentDto;
import com.nkedu.back.repository.ParentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

	private final ParentRepository parentRepository;

	@Override
	public boolean createParent(ParentDto parentDto) {
		try {
			Parent parent = new Parent();
			parent.setUserId(parentDto.getUserId());
			parent.setUserPw(parentDto.getUserPw());
			parent.setName(parentDto.getName());
			parent.setBirth(parentDto.getBirth());
			parent.setPhoneNumber(parentDto.getPhoneNumber());
			parent.setCreated(new Timestamp(System.currentTimeMillis()));;
			
			parentRepository.save(parent);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteParentById(Long id) {
		try {
			parentRepository.deleteById(id);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateParent(Long id, ParentDto parentDto) {
		try {
			Parent searchedParent = parentRepository.findById(id).get();
			
			if(ObjectUtils.isEmpty(searchedParent))
				return false;
			
			if(!ObjectUtils.isEmpty(parentDto.getUserId()))
				searchedParent.setUserId(parentDto.getUserId());
			if(!ObjectUtils.isEmpty(parentDto.getUserPw()))
				searchedParent.setUserPw(parentDto.getUserPw());
			if(!ObjectUtils.isEmpty(parentDto.getName()))
				searchedParent.setName(parentDto.getName());
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
				parentDto.setUserId(parent.getUserId());
				parentDto.setName(parent.getName());
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
	public ParentDto getParentById(Long id) {
		
		try {
			Parent parent = parentRepository.findById(id).get();
			
			ParentDto parentDto = new ParentDto();
			parentDto.setId(parent.getId());
			parentDto.setUserId(parent.getUserId());
			parentDto.setName(parent.getName());
			parentDto.setPhoneNumber(parent.getPhoneNumber());
			parentDto.setBirth(parent.getBirth());
			
			return parentDto;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

}

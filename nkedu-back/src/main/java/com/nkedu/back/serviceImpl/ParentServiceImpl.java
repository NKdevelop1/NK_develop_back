package com.nkedu.back.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.nkedu.back.api.ParentService;
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

	@Override
	public boolean createParent(ParentDto parentDto) {
		try {
			/* SuperBuilder �����ؼ� ���� �� ��Ȳ. �׽�Ʈ�� ���Ͽ� ��� ������.
			Parent parent = new Parent();
			parent.setUsername(parentDto.getUsername());
			parent.setPassword(parentDto.getPassword());
			parent.setNickname(parentDto.getNickname());
			parent.setBirth(parentDto.getBirth());
			parent.setPhoneNumber(parentDto.getPhoneNumber());
			parent.setCreated(new Timestamp(System.currentTimeMillis()));;
			
			parentRepository.save(parent);
			*/
			
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
	public ParentDto getParentById(Long id) {
		
		try {
			Parent parent = parentRepository.findById(id).get();
			
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

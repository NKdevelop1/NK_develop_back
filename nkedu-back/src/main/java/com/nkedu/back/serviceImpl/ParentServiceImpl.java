package com.nkedu.back.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.nkedu.back.api.ParentService;
import com.nkedu.back.model.Parent;
import com.nkedu.back.repository.ParentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

	private final ParentRepository parentRepository;

	@Override
	public Parent createParent(Parent parent) {
		Parent savedParent = parentRepository.save(parent);
		return savedParent;
	}

	@Override
	public void deleteParent(Long id) {
		parentRepository.deleteById(id);
	}

	@Override
	public Parent updateParent(Parent parent) {
		Parent updatedParent = null;
		
		try {
			Parent searchedParent = getParent(parent.getId());
			
			if(!ObjectUtils.isEmpty(searchedParent)) {
				updatedParent = parentRepository.save(parent);
			}
			
			return updatedParent;
			
		} catch (Exception e) {
			log.info("[Failed] e: " + e.toString()); 
		}
		
		return null;
	}

	@Override
	public List<Parent> getParents() {
		return parentRepository.findAll();
	}

	@Override
	public Parent getParent(Long id) {
		return parentRepository.findById(id).get();
	}

}

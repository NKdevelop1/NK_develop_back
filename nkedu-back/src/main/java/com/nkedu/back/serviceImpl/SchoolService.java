package com.nkedu.back.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nkedu.back.model.School;
import com.nkedu.back.model.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService  {
	private final SchoolRepository schoolRepository;
	
	
    // 등록된 모든 학교 리스트 조회
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }
    
	// 학교 추가 
	public School createSchool(School school) {
		return schoolRepository.save(school);
	}
	
    // 등록된 학교 삭제 
    public void deleteSchool(Long schoolId) {
    	schoolRepository.deleteById(schoolId);
    }
   
}

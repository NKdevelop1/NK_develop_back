package com.nkedu.back.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.model.School;
import com.nkedu.back.serviceImpl.SchoolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class SchoolController {

	private final SchoolService schoolService;
	
	// 등록된 모든 학교 리스트 조회 
	@GetMapping("/school")
	public ResponseEntity getAllSchools() {
		return ResponseEntity.ok(schoolService.getAllSchools());
	}
	
	// 학교 추가 - body에 ~고등학교
	@PostMapping("/school")
	public ResponseEntity<School> createSchool (@RequestBody School school){
		return ResponseEntity.ok(schoolService.createSchool(school));
	}
	
	// 학생 계정 삭제 
	@DeleteMapping("/school/{schoolId}")
	public ResponseEntity<Optional<School>> deleteSchool (@PathVariable Long schoolId){
		schoolService.deleteSchool(schoolId);
		return ResponseEntity.ok().build();
	}
	
	// 학생 계정 세부정보 조회 
	
	// 학생 계정 설정 
}

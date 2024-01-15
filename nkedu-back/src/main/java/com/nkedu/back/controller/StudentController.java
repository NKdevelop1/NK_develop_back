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

import com.nkedu.back.model.Student;
import com.nkedu.back.serviceImpl.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor

public class StudentController  {
	
	private final StudentService studentService;
	
	// 전체 학생 계정 리스트 조회 
	@GetMapping("/student")
	public ResponseEntity getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	// 특정 학생 계정 정보 조회 
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long studentId){
	    return ResponseEntity.ok(studentService.getStudentById(studentId));
	}
	
	// 학생 계정 생성 - body에 값 넣어서 Post
	@PostMapping("/student")
	public ResponseEntity<Student> createStudent (@RequestBody Student student){
		return ResponseEntity.ok(studentService.createStudent(student));
	}
	
	// 학생 계정 삭제 
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Optional<Student>> deleteStudent(@PathVariable Long studentId){
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok().build();
	}
	
	// 학생 계정 세부정보 조회 
	
	// 학생 계정 설정 
}

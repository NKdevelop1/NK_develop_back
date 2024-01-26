package com.nkedu.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.model.SchoolDTO;
import com.nkedu.back.model.Student;
import com.nkedu.back.model.StudentDTO;
import com.nkedu.back.api.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class StudentController  {
	
	private final StudentService studentService;
	
	// 전체 학생 계정 리스트 조회 
	@GetMapping("/student")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<StudentDTO> studentDTOs = studentService.getAllStudents();
		
		if (studentDTOs != null) {
			return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// 학생 계정 생성 - body에 값 넣어서 Post
	@PostMapping("/student")
	public ResponseEntity<Void> createStudent (@Validated @RequestBody StudentDTO studentDTO){
		return studentService.createStudent(studentDTO) ?
			       new ResponseEntity<>(null, HttpStatus.OK) :
			       new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	// 학생 계정 삭제 
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
		return studentService.deleteStudentById(studentId) ?
			       new ResponseEntity<>(null, HttpStatus.OK) :
			       new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// 학생 계정 설정 
	@PutMapping("/student/{studentId}")
	public ResponseEntity<Void> updateSchool (@PathVariable Long studentId, @RequestBody StudentDTO studentDTO){
		return studentService.updateStudent(studentId,studentDTO) ?
			       new ResponseEntity<>(null, HttpStatus.OK) :
			       new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정 학생 계정 정보 조회 
	@GetMapping("/student/{studentId}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId) {
		
		StudentDTO studentDTO = studentService.getStudentById(studentId);
		
		if (studentDTO != null) {
			return new ResponseEntity<>(studentDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	// 학생 계정 세부정보 조회 
}

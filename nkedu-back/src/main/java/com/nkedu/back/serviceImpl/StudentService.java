package com.nkedu.back.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nkedu.back.model.Student;
import com.nkedu.back.model.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService  {
	private final StudentRepository studentRepository;
	
    // 전체 학생 계정 리스트 조회
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // 특정 학생 계정 정보 조회
    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }
	
	// 학생 계정 생성 
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
    // 학생 계정 삭제
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    
    // 학생 계정 세부정보 조회
    
    // 학생 계정 생성 
}

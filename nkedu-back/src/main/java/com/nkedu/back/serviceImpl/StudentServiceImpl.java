package com.nkedu.back.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nkedu.back.api.StudentService;
import com.nkedu.back.model.School;
import com.nkedu.back.model.SchoolDTO;
import com.nkedu.back.model.Student;
import com.nkedu.back.model.StudentDTO;
import com.nkedu.back.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService  {
	
	private final StudentRepository studentRepository;
	
	// 학생 계정 생성 
	public boolean createStudent(StudentDTO studentDTO) {		
		try {
			Student student = new Student();
			student.setName(studentDTO.getName());
			studentRepository.save(student);
			return true;
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		return false;
	}
	
    // 학생 계정 삭제
    public boolean deleteStudentById(Long studentId) {
        try{
        	studentRepository.deleteById(studentId);
        	return true;
        } catch (Exception e){
    		log.info("failed e : " + e.getMessage());
        }
        return false;
    }
    
    // 학생 계정 수정
	public boolean updateStudent(Long id, StudentDTO studentDTO) {
		try {
			Student searchedStudent = studentRepository.findById(id).get();
			
			if(ObjectUtils.isEmpty(searchedStudent))
				return false;
			
			// 요청 받은 학교 이름으로 업데이트 
			if(!ObjectUtils.isEmpty(studentDTO.getName()))
				searchedStudent.setName(studentDTO.getName());
			
			studentRepository.save(searchedStudent);
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}
	
    // 모든 학생  계정 리스트 조회
    public List<StudentDTO> getAllStudents() {
    	try {
    		List<StudentDTO> studentDTOs = new ArrayList<>();
    		
			List<Student> students = studentRepository.findAll();
			
			for(Student student : students) {
				StudentDTO studentDTO = new StudentDTO();
				
				studentDTO.setId(student.getId());
				studentDTO.setName(student.getName());
				
				studentDTOs.add(studentDTO);
			}
			
			return studentDTOs;
		} catch(Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return null;    }
    
    // 특정 학생 계정 정보 조회
    public StudentDTO getStudentById(Long studentId) {
		try {
			Student student = studentRepository.findById(studentId).get();
			
			StudentDTO studentDto = new StudentDTO();
			studentDto.setId(student.getId());
			studentDto.setUserId(student.getUserId());
			studentDto.setName(student.getName());
			studentDto.setPhoneNumber(student.getPhoneNumber());
			studentDto.setBirth(student.getBirth());
			
			return studentDto;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return null;    
	}
	
    
    // 학생 계정 세부정보 조회
    
}

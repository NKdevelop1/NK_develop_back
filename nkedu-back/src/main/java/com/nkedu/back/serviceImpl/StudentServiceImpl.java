package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nkedu.back.api.StudentService;
import com.nkedu.back.model.School;
import com.nkedu.back.model.Student;
import com.nkedu.back.model.StudentDTO;
import com.nkedu.back.repository.SchoolRepository;
import com.nkedu.back.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService  {

	private final StudentRepository studentRepository;
	private final SchoolRepository schoolRepository;

	// 모든 학생  계정 리스트 조회
	public List<StudentDTO> getAllStudents() {
		try {
			// 업데이트된 studentDTO 담을 배
			List<StudentDTO> studentDTOs = new ArrayList<>();

			List<Student> students = studentRepository.findAll();

			for(Student student : students) {
				StudentDTO studentDTO = new StudentDTO();

				studentDTO.setId(student.getId());
				studentDTO.setUsername(student.getUsername());
				studentDTO.setNickname(student.getNickname());
				studentDTO.setBirth(student.getBirth());
				studentDTO.setPhoneNumber(student.getPhoneNumber());

				studentDTO.setSchool(student.getSchool());
				studentDTO.setGrade(student.getGrade());

				studentDTOs.add(studentDTO);
			}
			return studentDTOs;
		} catch(Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return null;
	}

	// 학생 계정 생성
	public boolean createStudent(StudentDTO studentDTO) {
		try {
			//1. 요청온 학교가 기존 학교 DB에 존재하는지 여부 판단하는 Logic

			// 요청받은 School 객체
			School postedSchool = studentDTO.getSchool();

			// 요청받은 School 객체가 불충분할 경우
			if(postedSchool == null || postedSchool.getId() == null || postedSchool.getSchoolName()==null) {
				throw new IllegalArgumentException("요청하신 학교의 정보가 충분하지 않습니다.");
			}

			// 요청받은 School 객체가 기존 schoolRepo에 존재한다면, searchedSchool 제대로 할당
			// 없다면, existingSchool = null로 할당
			School searchedSchool = schoolRepository.findById(postedSchool.getId()).orElse(null);

			// 1-1. 해당 id의 school이 존재하지 않을 때
			// if (searchedSchool == null) {
			//	throw new NullPointerException("요청하신 " + postedSchool.getSchoolName() +"은 잘못된 요청입니다.");
			//}
			// 1의 경우 자동으로 null 값이면  nullpointer로 알아서 처리

			// 2-1. id가 존재해도, (해당 id의 학교 이름 != 요청된 학교의 이름) 일 경우
			if(!searchedSchool.getSchoolName().equals(postedSchool.getSchoolName())) {
				throw new IllegalArgumentException("요청하신 id에 해당하는 학교와 요청한 " + postedSchool.getSchoolName() +"이 다른 학교입니다.");
			}


			//2. 학생 생성
			Student student = new Student();

			student.setUsername(studentDTO.getUsername());
			student.setPassword(studentDTO.getPassword());
			student.setNickname(studentDTO.getNickname());
			student.setCreated(new Timestamp(System.currentTimeMillis()));
			student.setPhoneNumber(studentDTO.getPhoneNumber());
			student.setBirth(studentDTO.getBirth());


			student.setSchool(postedSchool); //or searchedSchool?
			student.setGrade(studentDTO.getGrade());


			studentRepository.save(student);

			return true;
		} catch (IllegalArgumentException e) {
			log.error("Failed: " + e.getMessage(), e);
		} catch (Exception e) {
			log.error("Failed: " + e.getMessage(), e);
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
	// 학교 수정 API는 따로 만들어야 할 듯 (ex. 관리자)
	// grade 도 매년 자동으로 올릴지.. 어떻게 해야할지 고민
	public boolean updateStudent(Long studentId, StudentDTO studentDTO) {
		try {
			Student searchedStudent = studentRepository.findById(studentId).get();

			if(ObjectUtils.isEmpty(searchedStudent))
				return false;

			// 요청 받은 학생 이름으로 업데이트
			if(!ObjectUtils.isEmpty(studentDTO.getUsername()))
				searchedStudent.setUsername(studentDTO.getUsername());
			if(!ObjectUtils.isEmpty(studentDTO.getPassword()))
				searchedStudent.setPassword(studentDTO.getPassword());
			if(!ObjectUtils.isEmpty(studentDTO.getNickname()))
				searchedStudent.setNickname(studentDTO.getNickname());
			if(!ObjectUtils.isEmpty(studentDTO.getPhoneNumber()))
				searchedStudent.setPhoneNumber(studentDTO.getPhoneNumber());
			if(!ObjectUtils.isEmpty(studentDTO.getBirth()))
				searchedStudent.setBirth(studentDTO.getBirth());

			if(!ObjectUtils.isEmpty(studentDTO.getGrade()))
				searchedStudent.setGrade(studentDTO.getGrade());

			studentRepository.save(searchedStudent);
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}


	// 특정 학생 계정 정보 조회
	// 어떤 정보만을 넘길지는 추후 피드백을 통해 API 추가로 만들면 됨
	public StudentDTO getStudentById(Long studentId) {
		try {
			Student student = studentRepository.findById(studentId).get();

			// 특정 학생의 계정 정보를 담을 DTO 생성
			StudentDTO studentDTO = new StudentDTO();

			studentDTO.setId(student.getId());
			studentDTO.setUsername(student.getUsername());
			studentDTO.setNickname(student.getNickname());
			studentDTO.setPhoneNumber(student.getPhoneNumber());
			studentDTO.setBirth(student.getBirth());

			studentDTO.setSchool(student.getSchool());
			studentDTO.setGrade(student.getGrade());

			return studentDTO;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return null;
	}


	// 학생 계정 세부정보 조회 -> 추후 관리자 측에서 어떤 정보를 보면 좋을지에 따라 추가 보완 예

}

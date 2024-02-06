package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.entity.Teacher;
import com.nkedu.back.model.TeacherDTO;

public interface TeacherService {

	/**
	 * 선생님 계정 생성 (토큰이 필요함)
	 * @param teacher
	 * @return Teacher
	 */
	public boolean createTeacher(TeacherDTO teacherDTO);

	/**
	 * 선생님 계정 삭제 (토큰이 필요함)
	 * @param id
	 */
	public boolean deleteByUsername(String username);

	/**
	 * 선생님 계정 설정 (토큰이 필요함)
	 * @param teacher
	 * @return
	 */
	public boolean updateTeacher(String username, TeacherDTO teacherDTO);

	/**
	 * 선생님 계정 리스트 조회
	 * @return List<Teacher>
	 */
	public List<TeacherDTO> getTeachers();

	/**
	 * 선생님 계정 정보 조회
	 * @param id
	 * @return Teacher
	 */
	public TeacherDTO findByUsername(String username);

}
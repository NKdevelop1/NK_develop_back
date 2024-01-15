package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.model.Teacher;
import com.nkedu.back.model.TeacherDto;

public interface TeacherService {

	/**
	 * 선생님 계정 생성 (토큰이 필요함)
	 * @param teacher
	 * @return Teacher
	 */
	public boolean createTeacher(TeacherDto teacherDto);

	/**
	 * 선생님 계정 삭제 (토큰이 필요함)
	 * @param id
	 */
	public boolean deleteTeacherById(Long id);
	
	/**
	 * 선생님 계정 설정 (토큰이 필요함)
	 * @param teacher
	 * @return
	 */
	public boolean updateTeacher(Long id, TeacherDto teacherDto);

	/**
	 * 선생님 계정 리스트 조회
	 * @return List<Teacher>
	 */
	public List<TeacherDto> getTeachers();

	/**
	 * 선생님 계정 정보 조회
	 * @param id
	 * @return Teacher
	 */
	public TeacherDto getTeacherById(Long id);

    /**
     * 선생님 계정 로그인
     * @param teacherDto
     * @return 
     */
    public String login(TeacherDto teacherdto);
	
}
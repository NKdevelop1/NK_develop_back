package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.dto.ParentDTO;
import com.nkedu.back.dto.ParentOfStudentDTO;

public interface ParentService {

	/**
	 * 부모님 계정 생성
	 * @param parentDTO
	 * @return Parent
	 */
	public boolean createParent(ParentDTO parentDTO);

	/**
	 * 부모님 계정 삭제
	 * @param username
	 */
	public boolean deleteByUsername(String username);
	
	/**
	 * 부모님 계정 설정
	 * @param parentDTO
	 * @return boolean
	 */
	public boolean updateParent(String username, ParentDTO parentDTO);

	/**
	 * 부모님 계정 리스트 조회
	 * @return List<Parent>
	 */
	public List<ParentDTO> getParents();

	/**
	 * 부모님 계정 정보 조회
	 * @param username
	 * @return ParentDTO
	 */
	public ParentDTO findByUsername(String username);

	/**
	 * 부모님 계정에 속한 학생 추가
	 * @param parentname, student-name
	 * @return ParentOfStudent
	 */
	public ParentOfStudentDTO createParentOfStudent(ParentOfStudentDTO parentOfStudentDTO);
	
	/**
	 * 부모님 계정에 속한 학생 리스트 조회
	 * @param parentname
	 * @return List<ParentOfStudentDTO> 
	 */
	public List<ParentOfStudentDTO> getParentOfStudentsByParentname(String parentname);
	
	/**
	 * 부모님 계정에 속한 학생 삭제
	 * @param ParentOfStudentDTO
	 * @return boolean
	 */
	public boolean deleteParentOfStudent(ParentOfStudentDTO parentOfStudentDTO);
	
}

package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.dto.HomeworkDTO;

/**
 * 숙제 API 인터페이스 코드입니다.
 * @author devtae
 *
 */

public interface HomeworkService {
	
	/**
	 * Homework 리스트 반환
	 * @param class_id
	 * @return
	 */
	public List<HomeworkDTO> getHomeworks(Long classId);
	
	/**
	 * Homework 리스트 변환 (학생 숙제에 대한 status 포함)
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public List<HomeworkDTO> getHomeworks(Long classId, String username);
	
	/**
	 * Homework 세부 조회 
	 * @param class_id
	 * @param homework_id
	 * @return
	 */
	public HomeworkDTO getHomework(Long classId, Long homeworkId);

	/**
	 * Homework 세부 조회 (학생 숙제에 대한 status 포함)
	 * @param classID
	 * @param homeworkId
	 * @param studentId
	 * @return
	 */
	public HomeworkDTO getHomework(Long classID, Long homeworkId, String username);
	
	/**
	 * Homework 삽입
	 * @param homeworkDTO
	 * @return
	 */
	public HomeworkDTO createHomework(HomeworkDTO homeworkDTO);
	
	/**
	 * Homework 업데이트
	 * @param homeworkDTO
	 * @return
	 */
	public HomeworkDTO updateHomework(HomeworkDTO homeworkDTO);
	
	/**
	 * Homework 삭제
	 * @param class_id
	 * @param homework_id
	 * @return
	 */
	public boolean deleteHomework(Long classId, Long homeworkId);

}

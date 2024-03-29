package com.nkedu.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.api.HomeworkOfStudentService;
import com.nkedu.back.dto.HomeworkOfStudentDTO;
import com.nkedu.back.entity.HomeworkOfStudent.Status;

import lombok.RequiredArgsConstructor;

/**
 * 숙제 제출 관련 Controller 코드 입니다.
 * @author devtae
 *
 */

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeworkOfStudentController {
	
	private final HomeworkOfStudentService homeworkOfStudentService;
	
	/**
	 * 숙제 제출 리스트 조회
	 * @param classroomId
	 * @param homeworkId
	 * @return
	 */
	@GetMapping("/classroom/{classroom_id}/homework/{homework_id}/submit")
	public ResponseEntity<List<HomeworkOfStudentDTO>> getHomeworkOfStudent(@PathVariable("classroom_id") Long classroomId,
																  @PathVariable("homework_id") Long homeworkId,
																  @RequestParam(value="filter", required=false) String filterOption) {
		// Get Parameter 에 따른 리스트 조회 기능 제공
		Status filterStatus = null;
		
		switch(Status.valueOf(filterOption)) {
		case TODO:
			filterStatus = Status.TODO;
			break;
		case COMPLETE:
			filterStatus = Status.COMPLETE;
			break;
		case REJECT:
			filterStatus = Status.REJECT;
			break;
		case SUBMIT:
			filterStatus = Status.REJECT;
			break;
		default:
		}
		
		List<HomeworkOfStudentDTO> homeworkOfStudentDTOs = homeworkOfStudentService.getHomeworkOfStudents(filterStatus);
		
		if (homeworkOfStudentDTOs != null) {
			return new ResponseEntity<>(homeworkOfStudentDTOs, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * 숙제 제출 세부 조회
	 * @param classroomId
	 * @param homeworkId
	 * @param homeworkOfStudentId
	 * @return
	 */
	@GetMapping("/classroom/{classroom_id}/homework/{homework_id}/submit/{submit_id}")
	public ResponseEntity<HomeworkOfStudentDTO> getHomeworkOfStudent(@PathVariable("classroom_id") Long classroomId,
																  @PathVariable("homework_id") Long homeworkId,
																  @PathVariable("submit_id") Long homeworkOfStudentId) {
		
		HomeworkOfStudentDTO homeworkOfStudentDTO = homeworkOfStudentService.getHomeworkOfStudent(homeworkOfStudentId);
		
		if (homeworkOfStudentDTO != null) {
			return new ResponseEntity<>(homeworkOfStudentDTO, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * 숙제 제출 생성
	 * @param classroomId
	 * @param homeworkId
	 * @param homeworkOfStudentDTO
	 * @return
	 */
	@PostMapping("/classroom/{classroom_id}/homework/{homework_id}/submit")
	public ResponseEntity<HomeworkOfStudentDTO> createHomeworkOfStudent(@PathVariable("classroom_id") Long classroomId,
																		@PathVariable("homework_id") Long homeworkId,
																		@RequestBody HomeworkOfStudentDTO homeworkOfStudentDTO) {
		
		homeworkOfStudentDTO.setHomeworkId(homeworkId);
		
		HomeworkOfStudentDTO homeworkOfStudentDTO_ = homeworkOfStudentService.createHomeworkOfStudent(homeworkOfStudentDTO);
		
		if (homeworkOfStudentDTO_ != null) {
			return new ResponseEntity<>(homeworkOfStudentDTO_, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 숙제 제출 수정 (검토 및 반려 진행 가능)
	 * @param classroomId
	 * @param homeworkId
	 * @param homeworkOfStudentId
	 * @param homeworkOfStudentDTO
	 * @return
	 */
	@PutMapping("/classroom/{classroom_id}/homework/{homework_id}/submit/{submit_id}")
	public ResponseEntity<HomeworkOfStudentDTO> updateHomeworkOfStudent(@PathVariable("classroom_id") Long classroomId,
																		@PathVariable("homework_id") Long homeworkId,
																		@PathVariable("submit_id") Long homeworkOfStudentId,
																		@RequestBody HomeworkOfStudentDTO homeworkOfStudentDTO) {

		HomeworkOfStudentDTO homeworkOfStudentDTO_ = homeworkOfStudentService.createHomeworkOfStudent(homeworkOfStudentDTO);
		
		if (homeworkOfStudentDTO_ != null) {
			return new ResponseEntity<>(homeworkOfStudentDTO_, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 숙제 제출 삭제
	 * @param classroomId
	 * @param homeworkId
	 * @param homeworkOfStudentId
	 * @return
	 */
	@DeleteMapping("/classroom/{classroom_id}/homework/{homework_id}/submit/{submit_id}")
	public ResponseEntity<Boolean> deleteHomeworkOfStudent(@PathVariable("classroom_id") Long classroomId,
															@PathVariable("homework_id") Long homeworkId,
															@PathVariable("submit_id") Long homeworkOfStudentId) {

		boolean result = homeworkOfStudentService.deleteHomeworkOfStudent(homeworkOfStudentId);
		
		if (result == true) {
			return new ResponseEntity<>(result, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}

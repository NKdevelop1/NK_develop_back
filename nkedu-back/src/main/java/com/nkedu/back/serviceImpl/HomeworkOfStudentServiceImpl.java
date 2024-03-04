package com.nkedu.back.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nkedu.back.api.HomeworkOfStudentService;
import com.nkedu.back.dto.HomeworkOfStudentDTO;
import com.nkedu.back.repository.HomeworkOfStudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkOfStudentServiceImpl implements HomeworkOfStudentService {

	private final HomeworkOfStudentRepository homeworkOfStudentRepository; 
	
	@Override
	public HomeworkOfStudentDTO getHomeworkOfStudent(Long homeworkId) {
		return null;
	}

	@Override
	public List<HomeworkOfStudentDTO> getHomeworkOfStudents() {
		return null;
	}

	@Override
	public HomeworkOfStudentDTO createHomeworkOfStudent(HomeworkOfStudentDTO homeworkOfStudentDTO) {
		return null;
	}

	@Override
	public boolean deleteHomeworkOfStudent(Long homeworkId) {
		return false;
	}

	@Override
	public HomeworkOfStudentDTO updateHomeworkOfStudent(Long homeworkId, HomeworkOfStudentDTO homeworkOfStudentDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}

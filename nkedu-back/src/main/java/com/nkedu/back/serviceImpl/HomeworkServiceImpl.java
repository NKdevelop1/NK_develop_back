package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nkedu.back.api.HomeworkService;
import com.nkedu.back.dto.HomeworkDTO;
import com.nkedu.back.entity.Classroom;
import com.nkedu.back.entity.Homework;
import com.nkedu.back.entity.Teacher;
import com.nkedu.back.repository.ClassroomRepository;
import com.nkedu.back.repository.HomeworkRepository;
import com.nkedu.back.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

	private final HomeworkRepository homeworkRepository;
	private final ClassroomRepository classroomRepository;
	private final TeacherRepository teacherRepository;
	
	@Override
	public List<HomeworkDTO> getHomeworks(Long classId) {
		
		try {
			List<Homework> homeworks = homeworkRepository.findAll();
			List<HomeworkDTO> homeworkDTOs = new ArrayList<HomeworkDTO>();
			
			for(Homework homework : homeworks) {
				homeworkDTOs.add(HomeworkDTO.builder()
										    .id(homework.getId())
										    .title(homework.getTitle())
										    .teacherId(homework.getTeacher().getId())
										    .created(homework.getCreated())
										    .deadline(homework.getDeadline())
										    .build());
			}
			
			return homeworkDTOs;
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		return null;
	}

	@Override
	public HomeworkDTO getHomework(Long classId, Long homeworkId) {

		try {
			Homework homework = homeworkRepository.findById(homeworkId).get();
			HomeworkDTO homeworkDTO = HomeworkDTO.builder()
											     .id(homework.getId())
												 .title(homework.getTitle())
												 .teacherId(homework.getTeacher().getId())
												 .created(homework.getCreated())
												 .deadline(homework.getDeadline())
												 .description(homework.getDescription())
												 .build();
			return homeworkDTO;
			
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		return null;
	}

	@Override
	public HomeworkDTO createHomework(HomeworkDTO homeworkDTO) {
		
		try {
			Classroom classroom = classroomRepository.findById(homeworkDTO.getClassroomId()).get();
			Teacher teacher = teacherRepository.findById(homeworkDTO.getTeacherId()).get();
			
			Homework homework = Homework.builder()
										.classroom(classroom)
										.teacher(teacher)
										.created(new Timestamp(System.currentTimeMillis()))
										.deadline(homeworkDTO.getDeadline())
										.title(homeworkDTO.getTitle())
										.description(homeworkDTO.getDescription())
										.build();
			
			homeworkRepository.save(homework);
			
			HomeworkDTO homeworkDTO_ = HomeworkDTO.builder()
												 .id(homework.getId())
												 .classroomId(homework.getClassroom().getId())
												 .teacherId(homework.getTeacher().getId())
												 .created(homework.getCreated())
												 .deadline(homework.getDeadline())
												 .title(homework.getTitle())
												 .description(homework.getDescription())
												 .build();
														 
			
			return homeworkDTO_;
			
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		return null;
	}

	@Override
	public HomeworkDTO updateHomework(HomeworkDTO homeworkDTO) {

		try {
			Homework searchedHomework = homeworkRepository.findById(homeworkDTO.getId()).get();
			
			if(ObjectUtils.isEmpty(searchedHomework))
				return null;
			
			if(!ObjectUtils.isEmpty(homeworkDTO.getTitle()))
				searchedHomework.setTitle(homeworkDTO.getTitle());
			if(!ObjectUtils.isEmpty(homeworkDTO.getDescription()))
				searchedHomework.setDescription(homeworkDTO.getDescription());
			if(!ObjectUtils.isEmpty(homeworkDTO.getDeadline()))
				searchedHomework.setDeadline(homeworkDTO.getDeadline());
			
			homeworkRepository.save(searchedHomework);
			
			return homeworkDTO;
			
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public boolean deleteHomework(Long classId, Long homeworkId) {

		try {
			Homework searchedHomework = homeworkRepository.findById(homeworkId).get();
			
			homeworkRepository.delete(searchedHomework);
			
			return true;
		} catch (Exception e) {
			log.info("Failed e : " + e.getMessage());
		}
		return false;
	}

}

package com.nkedu.back.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.HomeworkOfStudent.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeworkDTO {

	private Long id;
	
	private Long classroomId;
	
	@JsonProperty("classroom")
	private ClassroomDTO classroomDTO;
	
	private Long teacherId;
	
	@JsonProperty("teacher")
	private TeacherDTO teacherDTO;
	
	private String title;
	
	private String description;
	
	private Timestamp created;
	
	private Timestamp updated;
	
	private Timestamp deadline;
	
	private Status status;
	
}

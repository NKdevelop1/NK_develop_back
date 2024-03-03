package com.nkedu.back.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class HomeworkOfStudentDTO {
	
	private Long id;
	
	private Long homeworkId;
	
	private Long studentId;
	
	private Status status;
	
	private String feedback;
	
	private List<FileDataDTO> files;
	
}

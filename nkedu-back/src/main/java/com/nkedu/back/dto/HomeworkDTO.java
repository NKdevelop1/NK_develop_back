package com.nkedu.back.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

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
	
	private Long teacherId;
	
	private String title;
	
	private String description;
	
	private Timestamp created;
	
	private Timestamp deadline;
}

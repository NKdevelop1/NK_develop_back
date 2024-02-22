package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.ParentOfStudent.Relationship;

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
public class ParentOfStudentDTO {
	
	@JsonProperty(value="parent")
	private ParentDTO parentDTO;
	
	@JsonProperty(value="student")
	private StudentDTO studentDTO;
	
	private Relationship relationship;
	
}

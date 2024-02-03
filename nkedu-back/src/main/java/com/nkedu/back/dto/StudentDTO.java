package com.nkedu.back.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.nkedu.back.entity.School;
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
public class StudentDTO {
	
	private Long id;
	
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String nickname;
	
	private Date birth;
	
	private String phoneNumber;
	
	private School school;
	
	private Long grade;
}

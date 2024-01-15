package com.nkedu.back.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class TeacherDto {
	
	private Long id;
	
	private String userId;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // ¾²±â Àü¿ë
	private String userPw;
	
	private String name;
	
	private Date birth;
	
	private String phoneNumber;
	
}
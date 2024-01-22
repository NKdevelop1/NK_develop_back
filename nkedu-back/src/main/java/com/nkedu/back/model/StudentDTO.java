package com.nkedu.back.model;

import java.sql.Date;

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
	
	private String userId;
	
	private String userPw;
	
	private String name;
	
	private Date birth;
	
	private String phoneNumber;
	
	private String school;
	
	private String grade;
}

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
public class ParentDto {
	
	private Long id;
	
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 쓰기 전용
	private String password;
	
	private String nickname;
	
	private Date birth;
	
	private String phoneNumber;
	
}

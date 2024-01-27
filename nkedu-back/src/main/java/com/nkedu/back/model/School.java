package com.nkedu.back.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="school")
@JsonInclude(Include.NON_NULL)
public class School {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="schoolId")
	private Long id;
	
    // @Pattern(regexp = ".*고등학교$",message = "***고등학교 형태의 입력 가능합니다.") 
	// -> 추가하고 싶은데, Valid 관련 어노테이션을 못잡음 나중에 해결 후 추가 예정 
	@Column(name="schoolName", unique = true)
	private String schoolName;
}

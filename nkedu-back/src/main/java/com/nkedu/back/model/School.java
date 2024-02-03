package com.nkedu.back.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("school")
@JsonInclude(Include.NON_NULL)
public class School extends User{
    // @Pattern(regexp = ".*고등학교$",message = "***고등학교 형태의 입력 가능합니다.") 
	// -> 추가하고 싶은데, Valid 관련 어노테이션을 못잡음 나중에 해결 후 추가 예정 
	@Column(name="schoolName", unique = true)
	private String schoolName;
}

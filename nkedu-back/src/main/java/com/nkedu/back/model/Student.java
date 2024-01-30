package com.nkedu.back.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("student")
@JsonInclude(Include.NON_NULL)
public class Student extends User {
	@ManyToOne
	@JoinColumn(name = "schoolId")
	private School school;
	
	@Column(name="grade")
	private Long grade;
}

package com.nkedu.back.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name="parent_of_student")
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentOfStudent {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Parent parent;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@Column(name="relationship", length=10)
	private String relationship;
	
}

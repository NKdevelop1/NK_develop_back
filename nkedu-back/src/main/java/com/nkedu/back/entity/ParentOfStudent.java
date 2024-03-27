package com.nkedu.back.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author devtae
 * 학생에 대한 부모 관계 정의를 위한 Entity 코드입니다.
 */

@Entity
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
	
	@Enumerated(EnumType.STRING)
	@Column(name="relationship")
	private Relationship relationship;

	// 학생과의 관계에 대하여 enum 으로 정의
	public enum Relationship {
		FATHER,
		MOTHER,
		GRANDFATHER,
		GRANDMOTHER,
		NOK; // Next-Of-Kin
	}
}

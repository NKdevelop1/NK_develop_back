package com.nkedu.back.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="school")
public class School {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="id")
	private Long id;
	
    // @Pattern(regexp = ".*고등학교$"
	// -> 문자열 끝이 고등학교로 고정되는 제약조건 추가
	
	// unique 제약조건 추가 -> https://velog.io/@hwan2da/JPA-Columnuniquetrue-UniqueConstraints
	@Column(name="name", unique = true)
	private String name;
}

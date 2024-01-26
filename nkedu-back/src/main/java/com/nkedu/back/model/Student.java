package com.nkedu.back.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="student")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"userPw", "created"})
public class Student {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="studentId")
	private Long id;
	
	@Column(name="userId",length=50,nullable=false,unique = true)
	private String userId;
	
	@JsonIgnore // JSON 데이터 이동간에 숨김처리 https://velog.io/@hth9876/JsonIgnorePropertiesignoreUnknown-true
	@Column(name="userPw",length=100, nullable=false)
	private String userPw;
	
	@Column(name="name",length=50, nullable=false)
	private String name;
	
	@Column(name="created",nullable=false)
	private Timestamp created;
	
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="birth",nullable=false)
	private Date birth;
	
	@Column(name="phoneNumber",nullable=false)
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "schoolId")
	private School school;
	
	@Column(name="grade")
	private Long grade;
}

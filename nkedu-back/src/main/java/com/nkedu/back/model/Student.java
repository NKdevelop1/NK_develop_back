package com.nkedu.back.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

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
@Table(name="student")
public class Student {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="id")
	private Long id;
	
	@Column(name="userId")
	private String userId;
	
	//@JsonIgnore : JSON 데이터 이동간에 숨김처리 https://velog.io/@hth9876/JsonIgnorePropertiesignoreUnknown-true
	@Column(name="userPw")
	private String userPw;
	
	@Column(name="name")
	private String name;
	
	@Column(name="created")
	private Timestamp created;
	
	@Column(name="birth")
	private Date birth;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="school")
	private String school;
	
	@Column(name="grade")
	private Long grade;
}

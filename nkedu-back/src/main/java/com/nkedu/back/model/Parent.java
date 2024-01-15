package com.nkedu.back.model;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="parent")
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Parent {
	//@JsonIgnore
	@Id // primary key
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;
	
	@Column(name="userId", length=50, nullable=false, unique=true) // unique
	private String userId;

	@JsonIgnore
	@Column(name="userPw", length=100, nullable=false)
	private String userPw;
	
	@Column(name="name", length=50, nullable=false)
	private String name;
	
	@JsonIgnore
	@Column(name="created", nullable=false)
	private Timestamp created;
	
	@Column(name="birth", nullable=false)
	private Date birth;
	
	@Column(name="phoneNumber", nullable=false)
	private String phoneNumber;
	
	/*
	@ManyToMany
	@JoinTable(
			name="parentAuthority",
			joinColumns = {@JoinColumn(name="id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="authorityName", referencedColumnName="authorityName")})
	private Set<ParentAuthority> parentAuthorities;
	*/

}

package com.nkedu.back.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name="user")
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType=DiscriminatorType.STRING)
@JsonInclude(Include.NON_NULL)
public class User {

	@Id // primary key
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;
	
	@Column(name="username", length=50, nullable=false, unique=true) // unique
	private String username;

	@JsonIgnore
	@Column(name="password", length=100, nullable=false)
	private String password;
	
	@Column(name="nickname", length=50, nullable=false)
	private String nickname;
	
	@JsonIgnore
	@Column(name="created", nullable=false)
	private Timestamp created;
	
	@Column(name="birth", nullable=false)
	private Date birth;
	
	@Column(name="phone_number", nullable=false)
	private String phoneNumber;
	
	@ManyToMany
	@JoinTable(
			name="user_authority",
			joinColumns={@JoinColumn(name="id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="authority_name", referencedColumnName="authority_name")})
	private Set<Authority> authorities;
}

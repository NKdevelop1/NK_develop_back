package com.nkedu.back.model;

import java.sql.Timestamp;
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
@NoArgsConstructor // 기본 생성자 생성
@Setter
@Getter
@Entity
@Table(name="teacher")
public class Teacher {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name="id")
    private Long id;

    @Column(name="userId")
	private String userId;
	
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

    public Teacher(Long id, String userId, String userPw, String name, Timestamp created, Date birth, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.created = created;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

package com.nkedu.back.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity                             // JPA Entity임을 나타냄; -> 클래스가 데이터베이스 테이블과 매핑되는 Entity로 인식됨
@Table(name="teacher")              // Entity를 매핑할 때 teacher 테이블과 매핑됨
@Setter                             // 모든 필드에 대한 setter 메서드 생성
@Getter                             // 모든 필드에 대한 getter 메서드 생성
@SuperBuilder                       // 부모 클래스에 대한 빌더 패턴 생성
@AllArgsConstructor                 // 모든 필드를 인자로 받는 생성자 생성
@NoArgsConstructor                  // 기본 생성자 생성
@JsonInclude(Include.NON_NULL)      // Json 직렬화 시 'null' 값이 아닌 필드만 포함하도록 지정
public class Teacher {
    @Id // primary key
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name="userId", length=100, nullable=false)
	private String userId;
	
    @JsonIgnore // Json 직렬화 시 해당 필드 무시; 민감한 정보 노출 X
	@Column(name="userPw", length=100, nullable=false)
	private String userPw;
	
	@Column(name="name")
	private String name;
	
    @JsonIgnore
	@Column(name="created", nullable=false)
	private Timestamp created;
	
	@Column(name="birth", nullable=false)
	private Date birth;
	
	@Column(name="phoneNumber", nullable=false)
	private String phoneNumber;

}

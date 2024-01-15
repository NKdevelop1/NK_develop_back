package com.nkedu.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="teacherAuthority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAuthority {

	@Id
	@Column(name="authorityName", length=50)
	private String authorityName;
}
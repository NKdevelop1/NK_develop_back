package com.nkedu.back.dto;

import com.nkedu.back.entity.ParentOfStudent.Relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipDTO {
	
	private Relationship relationship;
	
}

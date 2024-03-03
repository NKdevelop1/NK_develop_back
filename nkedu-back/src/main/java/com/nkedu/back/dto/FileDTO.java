package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nkedu.back.entity.File.FileType;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {

	private Long id;
	
	private String name;
	
	private FileType type;
	
	private String path;
	
}

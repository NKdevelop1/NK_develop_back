package com.nkedu.back.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.api.ParentService;
import com.nkedu.back.dto.ParentDto;
import com.nkedu.back.dto.ParentOfStudentDTO;
import com.nkedu.back.dto.RelationshipDTO;
import com.nkedu.back.dto.StudentDTO;
import com.nkedu.back.entity.Parent;
import com.nkedu.back.entity.ParentOfStudent;
import com.nkedu.back.entity.Student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ParentController {
	private final ParentService parentService;
	
	@GetMapping("/parent")
	public ResponseEntity<List<ParentDto>> getParents() {
		List<ParentDto> parentDtos = parentService.getParents();
		
		if (parentDtos != null) {
			return new ResponseEntity<>(parentDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
		}
	}
	
	@GetMapping("/parent/{username}")
	public ResponseEntity<ParentDto> getParent(@PathVariable("username") String username) {
		// 본인 혹은 관리자만 열람 가능하도록 토큰 필요
		
		ParentDto parentDto = parentService.findByUsername(username);
		
		if (parentDto != null) {
			return new ResponseEntity<>(parentDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/parent/{username}")
	public ResponseEntity<Void> updateParent(@PathVariable("username") String username, @RequestBody ParentDto parentDto) {

		boolean result = parentService.updateParent(username, parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/parent")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> createParent(@Validated @RequestBody ParentDto parentDto) {
		
		boolean result = parentService.createParent(parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/parent/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteParent(@PathVariable("username") String username) {
	
		boolean result = parentService.deleteByUsername(username);
		
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/parent/{parentname}/student")
	public ResponseEntity<List<ParentOfStudentDTO>> getParentOfStudentsByParentname(@PathVariable("parentname") String parentname) {
		
		// 본인 확인 로직 필요
		
		List<ParentOfStudentDTO> parentOfStudentDTOs = parentService.getParentOfStudentsByParentname(parentname);
				
		if (parentOfStudentDTOs != null) {
			return new ResponseEntity<>(parentOfStudentDTOs, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/parent/{parentname}/student/{studentname}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ParentOfStudentDTO> createParentOfStudent(@PathVariable("parentname") String parentname,
																	@PathVariable("studentname") String studentname,
																	@Valid @RequestBody RelationshipDTO relationshipDTO) {
		
		ParentOfStudentDTO parentOfStudentDTO = ParentOfStudentDTO.builder()
												.parentDTO(ParentDto.builder().username(parentname).build())
												.studentDTO(StudentDTO.builder().username(studentname).build())
												.relationship(relationshipDTO.getRelationship())
												.build();
		
		parentOfStudentDTO = parentService.createParentOfStudent(parentOfStudentDTO);

		if (ObjectUtils.isNotEmpty(parentOfStudentDTO)) {
			return new ResponseEntity<>(parentOfStudentDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/parent/{parentname}/student/{studentname}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteParentOfStudent(@PathVariable("parentname") String parentname,
												      @PathVariable("studentname") String studentname) {
		boolean result = parentService.deleteParentOfStudent(ParentOfStudentDTO.builder()
															 .parentDTO(ParentDto.builder().username(parentname).build())
															 .studentDTO(StudentDTO.builder().username(studentname).build())
															 .build());
							
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

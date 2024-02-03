package com.nkedu.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import lombok.RequiredArgsConstructor;

//@Slf4j
@RequiredArgsConstructor
@RequestMapping("/parent")
@RestController
public class ParentController {
	private final ParentService parentService;
	
	@GetMapping
	public ResponseEntity<List<ParentDto>> getParents() {
		List<ParentDto> parentDtos = parentService.getParents();
		
		if (parentDtos != null) {
			return new ResponseEntity<>(parentDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GetMapping("/{parentId}")
	public ResponseEntity<ParentDto> getParent(@PathVariable("parentId") Long parentId) {
		// 토큰 필요
		
		ParentDto parentDto = parentService.getParentById(parentId);
		
		if (parentDto != null) {
			return new ResponseEntity<>(parentDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> createParent(@Validated @RequestBody ParentDto parentDto) {
		// 토큰 필요
		
		boolean result = parentService.createParent(parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{parentId}")
	public ResponseEntity<Void> updateParent(@PathVariable("parentId") Long parentId, @RequestBody ParentDto parentDto) {
		// 토큰 필요
		
		boolean result = parentService.updateParent(parentId, parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{parentId}")
	public ResponseEntity<Void> deleteParent(@PathVariable("parentId") Long parentId) {
		// 토큰 필요
		
		boolean result = parentService.deleteParentById(parentId);
		
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.nkedu.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import jakarta.validation.Valid;
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
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
		}
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<ParentDto> getParent(@PathVariable("username") String username) {
		// 본인 혹은 관리자만 열람 가능하도록 토큰 필요
		
		ParentDto parentDto = parentService.findByUsername(username);
		
		if (parentDto != null) {
			return new ResponseEntity<>(parentDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<Void> updateParent(@PathVariable("username") String username, @RequestBody ParentDto parentDto) {
		// 토큰 필요
		
		boolean result = parentService.updateParent(username, parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> createParent(@Validated @RequestBody ParentDto parentDto) {
		
		boolean result = parentService.createParent(parentDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteParent(@PathVariable("username") String username) {
		// 토큰 필요
		
		boolean result = parentService.deleteByUsername(username);
		
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

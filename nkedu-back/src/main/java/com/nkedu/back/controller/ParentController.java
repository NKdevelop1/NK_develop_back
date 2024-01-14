package com.nkedu.back.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
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
import com.nkedu.back.model.Parent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/parent")
@RestController
public class ParentController {
	private final ParentService parentService;
	
	@GetMapping
	public ResponseEntity<List<Parent>> getParents() {
		try {
			List<Parent> parents = parentService.getParents();
			for(Parent parent : parents) {
				parent.setUserId(null);
				parent.setUserPw(null);
				parent.setBirth(null);
				parent.setPhone_number(null);
				parent.setCreated(null);
			}
			return new ResponseEntity<>(parents, HttpStatus.OK);
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@GetMapping("/{parentId}")
	public ResponseEntity<Parent> getParent(@PathVariable("parentId") Long parentId) {
		// 토큰 필요
		try {
			Parent parent = parentService.getParent(parentId);
			parent.setUserPw(null);
			return new ResponseEntity<>(parent, HttpStatus.OK);
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping
	public ResponseEntity<Parent> createParent(@Validated @RequestBody Parent parent) {
		// 토큰 필요
		try {
			Parent createdParent = parentService.createParent(parent);
			createdParent.setUserPw(null);
			return new ResponseEntity<>(createdParent, HttpStatus.OK);	
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/{parentId}")
	public ResponseEntity<Parent> updateParent(@PathVariable("parentId") Long parentId, @RequestBody Parent parent) {
		// 토큰 필요
		try {
			Parent searchedParent = parentService.getParent(parentId);
			
			if(searchedParent == null)
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

			if(parent.getName() != null)
				searchedParent.setName(parent.getName());
			if(parent.getPhone_number() != null)
				searchedParent.setPhone_number(parent.getPhone_number());
			if(parent.getCreated() != null)
				searchedParent.setCreated(parent.getCreated());
			if(parent.getBirth() != null)
				searchedParent.setBirth(parent.getBirth());
			
			Parent updatedParent = parentService.updateParent(searchedParent);
			updatedParent.setUserPw(null);
			return new ResponseEntity<>(updatedParent, HttpStatus.OK);			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{parentId}")
	public ResponseEntity<Boolean> deleteParent(@PathVariable("parentId") Long parentId) {
		// 토큰 필요
		try {
			parentService.deleteParent(parentId);
			return new ResponseEntity<>(true, HttpStatus.OK);			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

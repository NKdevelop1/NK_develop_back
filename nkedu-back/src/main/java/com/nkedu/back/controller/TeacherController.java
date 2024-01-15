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

import com.nkedu.back.api.TeacherService;
import com.nkedu.back.model.TeacherDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {
	private final TeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<List<TeacherDto>> getTeachers() {
		List<TeacherDto> teacherDtos = teacherService.getTeachers();
		
		if (teacherDtos != null) {
			return new ResponseEntity<>(teacherDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<TeacherDto> getTeacher(@PathVariable("teacherId") Long teacherId) {
		// 토큰 필요
		
		TeacherDto teacherDto = teacherService.getTeacherById(teacherId);
		
		if (teacherDto != null) {
			return new ResponseEntity<>(teacherDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> createTeacher(@Validated @RequestBody TeacherDto teacherDto) {
		// 토큰 필요
		
		boolean result = teacherService.createTeacher(teacherDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{teacherId}")
	public ResponseEntity<Void> updateTeacher(@PathVariable("teacherId") Long teacherId, @RequestBody TeacherDto teacherDto) {
		// 토큰 필요
		
		boolean result = teacherService.updateTeacher(teacherId, teacherDto);
		
		if (result == true) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") Long teacherId) {
		// 토큰 필요
		
		boolean result = teacherService.deleteTeacherById(teacherId);
		
		if (result == true) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody TeacherDto teacherDto) {
        try {
            String token = teacherService.login(teacherDto);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
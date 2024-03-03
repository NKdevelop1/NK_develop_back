package com.nkedu.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.repository.HomeworkOfStudentRepository;
import com.nkedu.back.repository.HomeworkRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeworkController {
	private final HomeworkRepository homeworkRepository;
	private final HomeworkOfStudentRepository homeworkOfStudentRepository;
	
}

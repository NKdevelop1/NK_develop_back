package com.nkedu.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nkedu.back.api.FileDataService;
import com.nkedu.back.dto.FileDataDTO;
import com.nkedu.back.repository.HomeworkOfStudentRepository;
import com.nkedu.back.repository.HomeworkRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author devtae
 * File 다운로드 및 업로드 컨트롤러 코드입니다.
 */

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FileDataController {
	
	private final FileDataService fileDataService;
	
	@PostMapping("/file")
	public ResponseEntity<FileDataDTO> uploadFile(@RequestParam("image") MultipartFile file) {
		FileDataDTO fileDataDTO = fileDataService.uploadFile(file);
		
		if (fileDataDTO != null) {
			return new ResponseEntity<>(fileDataDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
		
	@GetMapping("/file/{id}")
	public ResponseEntity<?> downloadFile(@PathVariable("id") Long id) {
		FileDataDTO fileDataDTO = fileDataService.downloadFile(id);
		
		if (fileDataDTO != null) {
			return ResponseEntity.status(HttpStatus.OK)
								 .contentType(MediaType.valueOf(fileDataDTO.getType().getMimeType()))
								 .body(fileDataDTO.getBytes());
			
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}

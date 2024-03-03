package com.nkedu.back.api;

import org.springframework.web.multipart.MultipartFile;

import com.nkedu.back.dto.FileDataDTO;

/**
 * @author devtae
 * 파일 데이터 업로드 및 다운로드 구현을 위한 API 인터페이스 파일입니다.
 */

public interface FileDataService {
	
	/**
	 * 파일 업로드 진행하는 API 입니다.
	 */
	public FileDataDTO uploadFile(MultipartFile file);
	
	/**
	 * Amazon S3 를 활용하여 파일 업로드 진행하는 API 입니다.
	 */
	public FileDataDTO uploadFileS3(MultipartFile file);
	
	/**
	 * 파일 다운로드를 진행하는 API 입니다. 
	 */
	public byte[] downloadFile(Long id);
	
}

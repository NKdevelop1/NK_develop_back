package com.nkedu.back.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nkedu.back.api.FileDataService;
import com.nkedu.back.dto.FileDataDTO;
import com.nkedu.back.entity.FileData;
import com.nkedu.back.entity.FileData.FileType;
import com.nkedu.back.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author devtae
 * 파일 저장 및 다운로드에 대한 API 구현 코드입니다.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDataServiceImpl implements FileDataService {

	private final FileDataRepository fileDataRepository;
	
	@Override
	public FileDataDTO uploadFile(MultipartFile multipartFile) {
		try {
			// 이미지 저장 폴더 확인
			String currentDirectory = System.getProperty("user.dir");
			String tempDirectoryPath = currentDirectory + File.separator + "temp";

		    // 임시 폴더 생성
		    File tempDirectory = new File(tempDirectoryPath);
		    if (!tempDirectory.exists())
		        tempDirectory.mkdir();
			
			//String name = multipartFile.getOriginalFilename();
		    String name = UUID.randomUUID().toString();
			FileType type = FileType.getFileType(multipartFile.getContentType());
			String path = tempDirectoryPath + File.separator + name;
			
			// FileType 에 대한 예외처리 진행 (현재, 사진 파일만 받을 수 있음.)
			if (type == null) throw new Exception("FileDataServiceImpl.uploadFile got unexpected filetype.");
			
			// 파일 저장
			File file = new File(path);
			multipartFile.transferTo(file);
			
			FileData fileData = fileDataRepository.save(FileData.builder()
																.name(name)
																.type(type)
																.path(file.getAbsolutePath())
																.build()
														);
			
			FileDataDTO fileDTO = FileDataDTO.builder()
											 .id(fileData.getId())
									 		 .name(fileData.getName())
									 		 .type(fileData.getType())
									 		 .build();

			return fileDTO;

		} catch (Exception e) {
			log.error("Failed: " + e.getMessage(),e);
		}
		
		return null;
	}
		
	@Override
	public FileDataDTO uploadFileS3(MultipartFile file) {
		return null;
	}

	@Override
	public FileDataDTO downloadFile(Long id) {
		try {
			FileData fileData = fileDataRepository.findById(id)
												  .orElseThrow(RuntimeException::new);
			
			String path = fileData.getPath();
			
			return FileDataDTO.builder()
							  .type(fileData.getType())
							  .bytes(Files.readAllBytes(new File(path).toPath()))
							  .build();
			
		} catch (Exception e) {
			log.error("Failed: " + e.getMessage(),e);
		}
		
		return null;
	}

}

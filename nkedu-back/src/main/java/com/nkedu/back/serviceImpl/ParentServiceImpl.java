package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.nkedu.back.api.ParentService;
import com.nkedu.back.entity.Authority;
import com.nkedu.back.entity.Parent;
import com.nkedu.back.entity.ParentOfStudent;
import com.nkedu.back.entity.ParentOfStudent.Relationship;
import com.nkedu.back.dto.ParentDto;
import com.nkedu.back.dto.ParentOfStudentDTO;
import com.nkedu.back.dto.StudentDTO;
import com.nkedu.back.repository.ParentOfStudentRepository;
import com.nkedu.back.repository.ParentRepository;
import com.nkedu.back.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

	private final ParentRepository parentRepository;
	private final StudentRepository studentRepository;
	private final ParentOfStudentRepository parentOfStudentRepository;
	private final PasswordEncoder passwordEncoder;

	
	/**
	 * 부모 엔티티 CRUD 관련 API 구현 코드입니다.
	 * @author DevTae
	 */
	
	@Override
	public boolean createParent(ParentDto parentDto) {
		try{
	        if (!ObjectUtils.isEmpty(parentRepository.findOneByUsername(parentDto.getUsername()))) {
	            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
	        }
	
	        System.out.println("getUserName: " + parentDto.getUsername());
	        
	        Set<Authority> authorities = new HashSet<Authority>();
	        
	        Authority authority_user = Authority.builder()
	                .authorityName("ROLE_USER")
	                .build();
	        authorities.add(authority_user);
	        
	        Authority authority_parent = Authority.builder()
	        		.authorityName("ROLE_PARENT")
	        		.build();
	        authorities.add(authority_parent);
	
	        Parent parent = (Parent) Parent.builder()
	                .username(parentDto.getUsername())
	                .password(passwordEncoder.encode(parentDto.getPassword()))
	                .nickname(parentDto.getNickname())
	                .birth(parentDto.getBirth())
	                .phoneNumber(parentDto.getPhoneNumber())
	                .authorities(authorities)
	                .created(new Timestamp(System.currentTimeMillis()))
	                .activated(true)
	                .build();
	
	        parentRepository.save(parent);
	        return true;
        } catch(Exception e) {
        	log.error("Failed: " + e.getMessage(),e);
        }
        return false;
	}

	@Override
	public boolean deleteByUsername(String username) {
		try {
			parentRepository.delete(parentRepository.findOneByUsername(username).get());
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateParent(String username, ParentDto parentDto) {
		try {
			Parent searchedParent = parentRepository.findOneByUsername(username).get();
			
			if(ObjectUtils.isEmpty(searchedParent))
				return false;
			
			if(!ObjectUtils.isEmpty(parentDto.getUsername()))
				searchedParent.setUsername(parentDto.getUsername());
			if(!ObjectUtils.isEmpty(parentDto.getPassword()))
				searchedParent.setPassword(parentDto.getPassword());
			if(!ObjectUtils.isEmpty(parentDto.getNickname()))
				searchedParent.setNickname(parentDto.getNickname());
			if(!ObjectUtils.isEmpty(parentDto.getPhoneNumber()))
				searchedParent.setPhoneNumber(parentDto.getPhoneNumber());	
			if(!ObjectUtils.isEmpty(parentDto.getBirth()))
				searchedParent.setBirth(parentDto.getBirth());
			
			parentRepository.save(searchedParent);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return false;
	}

	@Override
	public List<ParentDto> getParents() {
		try {
			List<ParentDto> parentDtos = new ArrayList<>();
			
			List<Parent> parents = parentRepository.findAll();
			
			for(Parent parent : parents) {
				ParentDto parentDto = new ParentDto();
				parentDto.setId(parent.getId());
				parentDto.setUsername(parent.getUsername());
				parentDto.setNickname(parent.getNickname());
				parentDto.setPhoneNumber(parent.getPhoneNumber());
				parentDto.setBirth(parent.getBirth());
				
				parentDtos.add(parentDto);
			}
			
			return parentDtos;
		} catch(Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

	@Override
	public ParentDto findByUsername(String username) {
		
		try {
			Parent parent = parentRepository.findOneByUsername(username).get();
			
			ParentDto parentDto = new ParentDto();
			parentDto.setId(parent.getId());
			parentDto.setUsername(parent.getUsername());
			parentDto.setNickname(parent.getNickname());
			parentDto.setPhoneNumber(parent.getPhoneNumber());
			parentDto.setBirth(parent.getBirth());
			
			return parentDto;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

	
	/**
	 * 학생 및 부모 사이의 관계 정의를 위한 서비스 구현 코드입니다.
	 * 
	 * @author DevTae
	 */
	
	@Override
	public ParentOfStudentDTO createParentOfStudent(ParentOfStudentDTO parentOfStudentDTO) {
		
		try {
			String parentname = parentOfStudentDTO.getParentDTO().getUsername();
			String studentname = parentOfStudentDTO.getStudentDTO().getUsername();
			Relationship relationship = parentOfStudentDTO.getRelationship();
			
			Optional<ParentOfStudent> optionalParentOfStudent = parentOfStudentRepository.findOneByParentnameAndStudentname(parentname, studentname);
				
			// 중복 등록 방지를 위한 조건문
			if (!ObjectUtils.isEmpty(optionalParentOfStudent)) {
				log.info("[Failed] Duplicated ParentOfStudent record occured. Skip it.");
				return null;
			}
			
			ParentOfStudent parentOfStudent = ParentOfStudent.builder()
								.parent(parentRepository.findOneByUsername(parentname).get())
								.student(studentRepository.findOneByUsername(studentname).get())
								.relationship(relationship)
								.build();
			
			parentOfStudentRepository.save(parentOfStudent);
			
			return parentOfStudentDTO;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<ParentOfStudentDTO> getParentOfStudentsByParentname(String parentname) {

		try {
			List<ParentOfStudent> parentOfStudents = parentOfStudentRepository.findAllByParentname(parentname).get();

			List<ParentOfStudentDTO> parentOfStudentDTOs = new ArrayList<>();
			
			for(ParentOfStudent parentOfStudent : parentOfStudents) {
				String parentname_each = parentOfStudent.getParent().getUsername();
				String studentname_each = parentOfStudent.getStudent().getUsername();
				Relationship relationship = parentOfStudent.getRelationship();
				
				ParentOfStudentDTO parentOfStudentDTO = ParentOfStudentDTO.builder()
															.parentDTO(ParentDto.builder().username(parentname_each).build())
															.studentDTO(StudentDTO.builder().username(studentname_each).build())
															.relationship(relationship)
															.build();
				
				parentOfStudentDTOs.add(parentOfStudentDTO);
			}
			
			return parentOfStudentDTOs;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public boolean deleteParentOfStudent(ParentOfStudentDTO parentOfStudentDTO) {
		
		try {
			String parentname = parentOfStudentDTO.getParentDTO().getUsername();
			String studentname = parentOfStudentDTO.getStudentDTO().getUsername();
			
			ParentOfStudent parentOfStudent = parentOfStudentRepository.findOneByParentnameAndStudentname(parentname, studentname).get();
			
			parentOfStudentRepository.delete(parentOfStudent);
			
			return true;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return false;
	}

}

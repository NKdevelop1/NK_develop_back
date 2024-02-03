package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.dto.ParentDto;

public interface ParentService {

	/**
	 * 부모님 계정 생성
	 * @param parentDto
	 * @return Parent
	 */
	public boolean createParent(ParentDto parentDto);

	/**
	 * 부모님 계정 삭제
	 * @param id
	 */
	public boolean deleteByUsername(String username);
	
	/**
	 * 부모님 계정 설정
	 * @param parent
	 * @return boolean
	 */
	public boolean updateParent(String username, ParentDto parentDto);

	/**
	 * 부모님 계정 리스트 조회
	 * @return List<Parent>
	 */
	public List<ParentDto> getParents();

	/**
	 * 부모님 계정 정보 조회
	 * @param id
	 * @return ParentDto
	 */
	public ParentDto findByUsername(String username);

	
}

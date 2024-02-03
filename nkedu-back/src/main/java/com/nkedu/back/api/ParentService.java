package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.dto.ParentDto;

public interface ParentService {

	/**
	 * 부모님 계정 생성 (토큰이 필요함)
	 * @param parent
	 * @return Parent
	 */
	public boolean createParent(ParentDto parentDto);

	/**
	 * 부모님 계정 삭제 (토큰이 필요함)
	 * @param id
	 */
	public boolean deleteParentById(Long id);
	
	/**
	 * 부모님 계정 설정 (토큰이 필요함)
	 * @param parent
	 * @return
	 */
	public boolean updateParent(Long id, ParentDto parentDto);

	/**
	 * 부모님 계정 리스트 조회
	 * @return List<Parent>
	 */
	public List<ParentDto> getParents();

	/**
	 * 부모님 계정 정보 조회
	 * @param id
	 * @return Parent
	 */
	public ParentDto getParentById(Long id);

	
}

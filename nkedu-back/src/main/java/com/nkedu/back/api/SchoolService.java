package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.model.SchoolDTO;

public interface SchoolService {

	public boolean createSchool(SchoolDTO schoolDTO);
	
    public boolean deleteSchoolById(Long id);

	public boolean updateSchool(Long id, SchoolDTO schoolDTO);
	
    public List<SchoolDTO> getAllSchools();
}

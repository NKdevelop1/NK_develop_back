package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.dto.ClassDTO;

public interface ClassService {

	public boolean createClass(ClassDTO classDTO);

	public boolean deleteByUsername(String className);

	public boolean updateClass(String className, ClassDTO classDTO);

	public List<ClassDTO> getClasses();

}
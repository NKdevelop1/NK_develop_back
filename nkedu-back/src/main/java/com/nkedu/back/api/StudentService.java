package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.model.StudentDTO;


public interface StudentService {
	
	public boolean createStudent(StudentDTO studentDTO);
	
    public boolean deleteStudentById(Long id);

	public boolean updateStudent(Long id, StudentDTO studentDTO);
	
    public List<StudentDTO> getAllStudents();
    
	public StudentDTO getStudentById(Long id);

}

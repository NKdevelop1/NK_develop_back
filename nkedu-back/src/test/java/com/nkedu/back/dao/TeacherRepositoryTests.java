package com.nkedu.back.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nkedu.back.model.Teacher;
import com.nkedu.back.repository.TeacherRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TeacherRepositoryTests {
    
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testGetTeachers() {
        List<Teacher> teacherList = teacherRepository.getTeachers();
        assertTrue(teacherList.size() > 0);
    }
}

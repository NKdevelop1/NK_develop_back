package com.nkedu.back.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nkedu.back.model.Teacher;

@Repository
public class TeacherRepository {
    
    public static List<Teacher> teachers;

    static{
        teachers = new ArrayList<>();
        teachers.add(new Teacher(2L, "nknk1", "nknk1!", "정현욱", null, null, "010-9919-6625"));
        teachers.add(new Teacher(20L, "nknk11", "nknk11!", "정현욱2", null, null, "010-9919-6625"));
        System.out.println(teachers);
    }

    public Map<String, String> getMessage() {
        return null;
    }

    public List<Teacher> getTeachers() {
        return TeacherRepository.teachers;
    }

    public Teacher getTeacherByUserId(String userId) {
        return teachers.stream()
                .filter(x -> x.getUserId().equals(userId))
                .findFirst()
                .orElse(new Teacher(0L, "Not Available", null, null, null, null, null));
    }

    public Teacher registTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teacher;
    }

    public void modifyUser(Teacher teacher) {
        teachers.stream()
                    .filter(item -> item.getUserId().equals(teacher.getUserId()))
                    .findAny()
                    .orElse(new Teacher(0L, "no user", null, null, null, null, null))
                    .setName(teacher.getName());
    }

    public void removeUser(String userId) {
        teachers.removeIf(teacher -> teacher.getUserId().equals(userId));
    }
}

package com.nkedu.back.api;

import java.util.List;

import com.nkedu.back.model.Teacher;

public interface TeacherService {
    public Teacher registTeacher(Teacher teacher);
    public void removeUser(String userId);
    public void modifyUser(Teacher teacher);
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherByUserId(String userId);

}

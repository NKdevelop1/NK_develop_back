package com.nkedu.back.service_impl;

import java.util.List;
import java.util.Map;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkedu.back.model.Teacher;
import com.nkedu.back.repository.TeacherRepository;

@Service
public class TeacherServiceImpl {
    
    @Autowired
    private TeacherRepository teacherRepository;

    public Map<String, String> getMessage() {
        Map<String, String> map = teacherRepository.getMessage();
        return map;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.getTeachers();
    }

    public Teacher getTeacherByUserId(String userId) {
        return teacherRepository.getTeacherByUserId(userId);
    }

    public Teacher registTeacher(Teacher teacher) {
        return teacherRepository.registTeacher(teacher);
    }

    public void modifyUser(Teacher teacher) {
        teacherRepository.modifyUser(teacher);
    }

    public void removeUser(String userId) {
        teacherRepository.removeUser(userId);
    }

    // public Teacher login(Teacher teacher) {
    //     noTeacherByUserId(teacher);
    //     noTeacherByPassword(teacher);

    //     String token = securityService.createToken(teacher.getUserId(), 2*1000*60);
    //     teacher.setJwt(token);
    //     return teacher;
    // }

    // private void NoTeacherByUserId(Teacher teacher) {
    //     if (teacherRepository.findByUserId(teacher.getUserId()).isEmpty()) {
    //         throw new IllegalStateException("아이디가 존재하지 않습니다.");
    //     }
    // }

    // private void NoTeacherByPassword(Teacher teacher) {
    //     Optional<Object> optoinalTeacher = teacherRepository.findByUserId(teacher.getUserId());
    //     if (optionalTeacher.isPresent()) {
    //         Teacher teacher = (Teacher) optionalTeacher.get();
    //         if (!teacher.getUserPw().equals(teacher.getUserPw())) {
    //             throw new IllegalStateException("비밀번호가 틀립니다.");
    //         }
    //     }
    // }
}

package com.nkedu.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nkedu.back.model.Teacher;
import com.nkedu.back.service_impl.TeacherServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    
    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping("")
    public List<Teacher> getAllTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{userId}")
    public Teacher getTeacherByUserId(@PathVariable("userId") String userId) {
        return teacherService.getTeacherByUserId(userId);
    }

    @PostMapping("")
    public Teacher registTeacher(@RequestBody Teacher teacher) {
        System.out.println(teacher);
        return teacherService.registTeacher(teacher);
    }

    @PutMapping("/{userId}")
    public void modifyUser(
            @PathVariable("userId") String userId,
            @RequestBody Teacher teacher) {
        teacherService.modifyUser(teacher);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable("userId") String userId) {
        teacherService.removeUser(userId);
    }

    @GetMapping("/login")
    public String loginForm() {
        return "teachers/loginTeacherForm";
    }

    // @PostMapping("/login")
    // public String login(Teacher teacher, Model model) {
    //         Teacher teacher = teacherService.login(teacher);
    //     if(!teacher.getJwt().isEmpty()) {
    //         model.addAttribute("teacherform", teacher.getJwt());
    //         return "home";
    //     }
    //     else {
    //         return "teachers/loginTeacherForm";
    //     }
    // }

}

package com.nkedu.back.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nkedu.back.entity.Authority;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nkedu.back.api.TeacherService;
import com.nkedu.back.entity.Teacher;
import com.nkedu.back.dto.TeacherDTO;
import com.nkedu.back.repository.TeacherRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean createTeacher(TeacherDTO teacherDTO) {
        try {
            if (!ObjectUtils.isEmpty(teacherRepository.findOneByUsername(teacherDTO.getUsername()))) {
                throw new RuntimeException("이미 가입되어 있는 유저입니다.");
            }

            System.out.println("getUserName: " + teacherDTO.getUsername());

            Set<Authority> authorities = new HashSet<Authority>();

            Authority authority_user = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
            authorities.add(authority_user);

            Authority authority_teacher = Authority.builder()
                    .authorityName("ROLE_TEACHER")
                    .build();
            authorities.add(authority_teacher);

            Teacher teacher = (Teacher) Teacher.builder()
                    .username(teacherDTO.getUsername())
                    .password(passwordEncoder.encode(teacherDTO.getPassword()))
                    .nickname(teacherDTO.getNickname())
                    .birth(teacherDTO.getBirth())
                    .phoneNumber(teacherDTO.getPhoneNumber())
                    .authorities(authorities)
                    .created(new Timestamp(System.currentTimeMillis()))
                    .activated(true)
                    .build();

            teacherRepository.save(teacher);
            return true;
        } catch (Exception e) {
            log.error("Failed: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteByUsername(String username) {
        try {
            teacherRepository.delete(teacherRepository.findOneByUsername(username).get());

            return true;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateTeacher(String username, TeacherDTO teacherDTO) {
        try {
            Teacher searchedTeacher = teacherRepository.findOneByUsername(username).get();

            if (ObjectUtils.isEmpty(searchedTeacher))
                return false;

            if (!ObjectUtils.isEmpty(teacherDTO.getUsername()))
                searchedTeacher.setUsername(teacherDTO.getUsername());
            if (!ObjectUtils.isEmpty(teacherDTO.getPassword()))
                searchedTeacher.setPassword(teacherDTO.getPassword());
            if (!ObjectUtils.isEmpty(teacherDTO.getNickname()))
                searchedTeacher.setNickname(teacherDTO.getNickname());
            if (!ObjectUtils.isEmpty(teacherDTO.getPhoneNumber()))
                searchedTeacher.setPhoneNumber(teacherDTO.getPhoneNumber());
            if (!ObjectUtils.isEmpty(teacherDTO.getBirth()))
                searchedTeacher.setBirth(teacherDTO.getBirth());

            teacherRepository.save(searchedTeacher);

            return true;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<TeacherDTO> getTeachers() {
        try {
            List<TeacherDTO> teacherDTOs = new ArrayList<>();

            List<Teacher> teachers = teacherRepository.findAll();

            for (Teacher teacher : teachers) {
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setId(teacher.getId());
                teacherDTO.setUsername(teacher.getUsername());
                teacherDTO.setNickname(teacher.getNickname());
                teacherDTO.setPhoneNumber(teacher.getPhoneNumber());
                teacherDTO.setBirth(teacher.getBirth());

                teacherDTOs.add(teacherDTO);
            }

            return teacherDTOs;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }

        return null;
    }

    @Override
    public TeacherDTO findByUsername(String username) {

        try {
            Teacher teacher = teacherRepository.findOneByUsername(username).get();

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setUsername(teacher.getUsername());
            teacherDTO.setNickname(teacher.getNickname());
            teacherDTO.setPhoneNumber(teacher.getPhoneNumber());
            teacherDTO.setBirth(teacher.getBirth());

            return teacherDTO;

        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }

        return null;
    }
}
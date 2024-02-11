package com.nkedu.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nkedu.back.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findOneByUsername(String username);
}

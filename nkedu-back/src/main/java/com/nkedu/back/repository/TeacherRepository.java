package com.nkedu.back.repository;

import com.nkedu.back.entity.Parent;
import com.nkedu.back.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Optional<Teacher> findOneByUsername(String username);
}

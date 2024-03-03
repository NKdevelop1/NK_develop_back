package com.nkedu.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {

}

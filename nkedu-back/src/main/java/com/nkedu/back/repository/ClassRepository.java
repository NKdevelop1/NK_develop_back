package com.nkedu.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
	
}

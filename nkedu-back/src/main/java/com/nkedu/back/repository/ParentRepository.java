package com.nkedu.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
	
	Optional<Parent> findOneByUsername(String username); 
}

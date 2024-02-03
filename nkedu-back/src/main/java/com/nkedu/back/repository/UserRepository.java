package com.nkedu.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    @EntityGraph(attributePaths="authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
    
    Optional<User> findOneByUsername(String username);
}
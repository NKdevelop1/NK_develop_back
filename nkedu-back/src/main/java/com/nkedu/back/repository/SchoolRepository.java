package com.nkedu.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}

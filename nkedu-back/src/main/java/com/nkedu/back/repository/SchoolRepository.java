package com.nkedu.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nkedu.back.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}

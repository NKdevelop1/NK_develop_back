package com.nkedu.back.repository;


import com.nkedu.back.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.School;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}

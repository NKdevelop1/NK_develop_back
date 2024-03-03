package com.nkedu.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}

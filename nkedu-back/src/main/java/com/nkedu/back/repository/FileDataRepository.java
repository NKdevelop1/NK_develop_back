package com.nkedu.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nkedu.back.entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

}

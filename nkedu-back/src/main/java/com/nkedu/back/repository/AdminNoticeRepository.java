package com.nkedu.back.repository;

import com.nkedu.back.entity.AdminNotice;
import com.nkedu.back.entity.ClassNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdminNoticeRepository extends JpaRepository<AdminNotice,Long> {

    Optional<AdminNotice> findOneById(Long id);


}

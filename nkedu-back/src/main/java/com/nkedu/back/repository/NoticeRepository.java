package com.nkedu.back.repository;

import com.nkedu.back.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    Optional<Notice> findOneById(Long id);
}

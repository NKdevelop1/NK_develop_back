package com.nkedu.back.repository;

import com.nkedu.back.entity.Notice;
import com.nkedu.back.entity.ParentOfStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    Optional<Notice> findOneById(Long id);

    // 특정 수업에 해당하는 공지 조회
    @Query("SELECT not FROM Notice not WHERE not.classroom.id = :classroom_id")
    Optional<List<Notice>> findAllByClassroomId(@Param("classroom_id") Long classroom_id);

    @Query("SELECT not FROM Notice not WHERE not.classroom.id = :classroom_id AND not.id = :notice_id")
    Optional<Notice> findOneByClassroomIdAndNoticeId(@Param("classroom_id") Long classroom_id, @Param("notice_id") Long notice_id);

}

package com.nkedu.back.api;

import com.nkedu.back.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {

    /**
     * 수업 생성
     * @param noticeDTO
     * @author beom-i
     */
    public boolean createNotice(NoticeDTO noticeDTO);

    /**
     * 수업 삭제
     * @param id
     * @author beom-i
     */
    public boolean deleteNoticeById(Long id);

    /**
     * 수업 설정 (param = 바꾸고 싶은 notice의 id, 바꿀 noticeDTO)
     * @param id, noticeDTO
     * @return boolean
     * @author beom-i
     */
    public boolean updateNotice(Long id, NoticeDTO noticeDTO);

    /**
     * 모든 수업 리스트 조회
     * @return List<NoticeDTO>
     * @author beom-i
     */
    public List<NoticeDTO> getNotices();

    /**
     * 특정 수업 조회
     * @param id
     * @return NoticeDTO
     * @author beom-i
     */
    public NoticeDTO getNoticeById(Long id);

    /**
     * 수업의 전체 공지 조회
     * @param classroom_id
     * @return NoticeDTO
     * @author beom-i
     */
    public List<NoticeDTO> getNoticesByClassroomId(Long classroom_id);

    /**
     * 수업의 특정 공지 조회
     * @param classroom_id notice_id
     * @return NoticeDTO
     * @author beom-i
     */
    public NoticeDTO getNoticeByClassroomIdAndNoticeId(Long classroom_id,Long notice_id);

}

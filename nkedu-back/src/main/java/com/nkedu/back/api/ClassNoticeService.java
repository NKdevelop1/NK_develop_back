package com.nkedu.back.api;

import com.nkedu.back.dto.ClassNoticeDTO;

import java.util.List;

public interface ClassNoticeService {

    /**
     * 수업 공지 생성
     * @param classNoticeDTO
     * @author beom-i
     */
    public boolean createClassNotice(ClassNoticeDTO classNoticeDTO);

    /**
     * 수업 공지 삭제
     * @param id
     * @author beom-i
     */
    public boolean deleteClassNoticeById(Long id);

    /**
     * 수업 공지 설정 (param = 바꾸고 싶은 classNotice의 id, 바꿀 classNoticeDTO)
     * @param id, classNoticeDTO
     * @return boolean
     * @author beom-i
     */
    public boolean updateClassNotice(Long id, ClassNoticeDTO classNoticeDTO);


    /**
     * 특정 수업 공지 조회
     * @param classroom_id
     * @return List<ClassNoticeDTO>
     * @author beom-i
     */
    public List<ClassNoticeDTO> getClassNoticesByClassroomId(Long classroom_id);

    /**
     * 특정 수업 공지 조회
     * @param classroom_id classNotice_id
     * @return ClassNoticeDTO
     * @author beom-i
     */
    public ClassNoticeDTO getClassNoticeByClassroomIdAndClassNoticeId(Long classroom_id,Long classNotice_id);

}

package com.nkedu.back.serviceImpl;

import com.nkedu.back.api.NoticeService;
import com.nkedu.back.dto.ClassroomDTO;
import com.nkedu.back.dto.NoticeDTO;
import com.nkedu.back.dto.UserDTO;
import com.nkedu.back.entity.Notice;
import com.nkedu.back.entity.Notice.NoticeType;
import com.nkedu.back.entity.User;
import com.nkedu.back.repository.ClassroomRepository;
import com.nkedu.back.repository.NoticeRepository;
import com.nkedu.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;

    /**
     * Classroom 엔티티의 CRUD 관련 API 구현 코드입니다.
     * @author beom-i
     */

    @Override
    public boolean createNotice(NoticeDTO noticeDTO) {

        try{
            String user_username = noticeDTO.getUserDTO().getUsername();
            Long classroom_id = noticeDTO.getClassroomDTO().getId();
            NoticeType noticeType = noticeDTO.getNoticeType();

            // Q. noticeDTO로 ID를 받지 않는데 어떻게 중복 검증을 할 수 있는가?
            if (!ObjectUtils.isEmpty(noticeRepository.findOneById(noticeDTO.getId()))) {
                throw new RuntimeException("이미 등록된 공지입니다.");
            }
            Notice notice = Notice.builder()
                    .user(userRepository.findOneByUsername(user_username).get())
                    .classroom(classroomRepository.findOneById(classroom_id).get())
                    .title(noticeDTO.getTitle())
                    .content(noticeDTO.getContent())
                    .noticeType(noticeType)
                    .created(new Timestamp(System.currentTimeMillis()))
                    .build();

            noticeRepository.save(notice);
            return true;
        } catch(Exception e) {
            log.error("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean deleteNoticeById(Long id) {
        try{
            noticeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.info("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean updateNotice(Long id, NoticeDTO noticeDTO) {

        try {
            Notice searchedNotice = noticeRepository.findOneById(id).get();

            if(ObjectUtils.isEmpty(searchedNotice))
                return false;

            if(!ObjectUtils.isEmpty(noticeDTO.getTitle()))
                searchedNotice.setTitle(noticeDTO.getTitle());
            if(!ObjectUtils.isEmpty(noticeDTO.getContent()))
                searchedNotice.setContent(noticeDTO.getContent());
            if(!ObjectUtils.isEmpty(noticeDTO.getNoticeType()))
                searchedNotice.setNoticeType(noticeDTO.getNoticeType());

            noticeRepository.save(searchedNotice);

            return true;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<NoticeDTO> getNotices() {
        try {
            List<NoticeDTO> noticeDTOs = new ArrayList<>();

            List<Notice> notices = noticeRepository.findAll();

            for(Notice notice : notices) {
                User user = userRepository.findOneByUsername(notice.getUser().getUsername()).get();

                if(ObjectUtils.isEmpty(user)) {
                    throw new RuntimeException("등록된 사용자가 존재하지 않습니다.");
                }

                NoticeDTO noticeDTO = NoticeDTO.builder()
                                .id(notice.getId())
                                .classroomDTO(ClassroomDTO.builder().id(notice.getClassroom().getId()).build())
                                .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                                .title(notice.getTitle())
                                .content(notice.getContent())
                                .noticeType(notice.getNoticeType())
                                .build();
                noticeDTOs.add(noticeDTO);
            }
            return noticeDTOs;
        } catch(Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return null;
    }

    @Override
    public NoticeDTO getNoticeById(Long id) {
        try{
            Notice notice = noticeRepository.findOneById(id).get();
            User user = userRepository.findOneByUsername(notice.getUser().getUsername()).get();


            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .id(notice.getId())
                    .classroomDTO(ClassroomDTO.builder().id(notice.getClassroom().getId()).build())
                    .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .noticeType(notice.getNoticeType())
                    .build();

            return noticeDTO;
        } catch(Exception e){
            log.info("Failed e : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<NoticeDTO> getNoticesByClassroomId(Long id) {
        try {
            List<NoticeDTO> noticeDTOs = new ArrayList<>();

            List<Notice> notices = noticeRepository.findAllByClassroomId(id).get();

            for(Notice notice : notices) {
                User user = userRepository.findOneByUsername(notice.getUser().getUsername()).get();

                if(ObjectUtils.isEmpty(user)) {
                    throw new RuntimeException("공지를 작성한 사용자가 존재하지 않습니다.");
                }

                NoticeDTO noticeDTO = NoticeDTO.builder()
                        .id(notice.getId())
                        .classroomDTO(ClassroomDTO.builder().id(notice.getClassroom().getId()).build())
                        .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .noticeType(notice.getNoticeType())
                        .build();
                noticeDTOs.add(noticeDTO);
            }
            return noticeDTOs;
        } catch(Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return null;
    }

    @Override
    public NoticeDTO getNoticeByClassroomIdAndNoticeId(Long classroom_id,Long notice_id) {
        try{
            Notice notice = noticeRepository.findOneByClassroomIdAndNoticeId(classroom_id,notice_id).get();
            User user = userRepository.findOneByUsername(notice.getUser().getUsername()).get();


            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .id(notice.getId())
                    .classroomDTO(ClassroomDTO.builder().id(notice.getClassroom().getId()).build())
                    .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .noticeType(notice.getNoticeType())
                    .build();

            return noticeDTO;
        } catch(Exception e){
            log.info("Failed e : " + e.getMessage());
        }
        return null;
    }

}

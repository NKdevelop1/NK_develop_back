package com.nkedu.back.serviceImpl;

import com.nkedu.back.api.ClassNoticeService;
import com.nkedu.back.dto.ClassroomDTO;
import com.nkedu.back.dto.ClassNoticeDTO;
import com.nkedu.back.dto.UserDTO;
import com.nkedu.back.entity.ClassNotice;
import com.nkedu.back.entity.ClassNotice.ClassNoticeType;
import com.nkedu.back.entity.User;
import com.nkedu.back.repository.ClassroomRepository;
import com.nkedu.back.repository.ClassNoticeRepository;
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
public class ClassNoticeServiceImpl implements ClassNoticeService {
    private final ClassNoticeRepository classNoticeRepository;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;

    /**
     * Classroom 엔티티의 CRUD 관련 API 구현 코드입니다.
     * @author beom-i
     */

    @Override
    public boolean createClassNotice(ClassNoticeDTO classNoticeDTO) {

        try{
            String user_username = classNoticeDTO.getUserDTO().getUsername();
            Long classroom_id = classNoticeDTO.getClassroomDTO().getId();
            ClassNoticeType classNoticeType = classNoticeDTO.getClassNoticeType();

            // Q. noticeDTO로 ID를 받지 않는데 어떻게 중복 검증을 할 수 있는가?
            if (!ObjectUtils.isEmpty(classNoticeRepository.findOneById(classNoticeDTO.getId()))) {
                throw new RuntimeException("이미 등록된 공지입니다.");
            }
            ClassNotice classNotice = ClassNotice.builder()
                    .user(userRepository.findOneByUsername(user_username).get())
                    .classroom(classroomRepository.findOneById(classroom_id).get())
                    .title(classNoticeDTO.getTitle())
                    .content(classNoticeDTO.getContent())
                    .classNoticeType(classNoticeType)
                    .created(new Timestamp(System.currentTimeMillis()))
                    .build();

            classNoticeRepository.save(classNotice);
            return true;
        } catch(Exception e) {
            log.error("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean deleteClassNoticeById(Long id) {
        try{
            classNoticeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.info("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean updateClassNotice(Long id, ClassNoticeDTO classNoticeDTO) {

        try {
            ClassNotice searchedClassNotice = classNoticeRepository.findOneById(id).get();

            if(ObjectUtils.isEmpty(searchedClassNotice))
                return false;

            if(!ObjectUtils.isEmpty(classNoticeDTO.getTitle()))
                searchedClassNotice.setTitle(classNoticeDTO.getTitle());
            if(!ObjectUtils.isEmpty(classNoticeDTO.getContent()))
                searchedClassNotice.setContent(classNoticeDTO.getContent());
            if(!ObjectUtils.isEmpty(classNoticeDTO.getClassNoticeType()))
                searchedClassNotice.setClassNoticeType(classNoticeDTO.getClassNoticeType());

            classNoticeRepository.save(searchedClassNotice);

            return true;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return false;
    }


    @Override
    public List<ClassNoticeDTO> getClassNoticesByClassroomId(Long id) {
        try {
            List<ClassNoticeDTO> classNoticeDTOs = new ArrayList<>();

            List<ClassNotice> classNotices = classNoticeRepository.findAllByClassroomId(id).get();

            for(ClassNotice classNotice : classNotices) {
                User user = userRepository.findOneByUsername(classNotice.getUser().getUsername()).get();

                if(ObjectUtils.isEmpty(user)) {
                    throw new RuntimeException("공지를 작성한 사용자가 존재하지 않습니다.");
                }

                ClassNoticeDTO classNoticeDTO = ClassNoticeDTO.builder()
                        .id(classNotice.getId())
                        .classroomDTO(ClassroomDTO.builder().id(classNotice.getClassroom().getId()).build())
                        .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                        .title(classNotice.getTitle())
                        .content(classNotice.getContent())
                        .classNoticeType(classNotice.getClassNoticeType())
                        .build();
                classNoticeDTOs.add(classNoticeDTO);
            }
            return classNoticeDTOs;
        } catch(Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return null;
    }

    @Override
    public ClassNoticeDTO getClassNoticeByClassroomIdAndClassNoticeId(Long classroom_id,Long classNotice_id) {
        try{
            ClassNotice classNotice = classNoticeRepository.findOneByClassroomIdAndClassNoticeId(classroom_id,classNotice_id).get();
            User user = userRepository.findOneByUsername(classNotice.getUser().getUsername()).get();


            ClassNoticeDTO classNoticeDTO = ClassNoticeDTO.builder()
                    .id(classNotice.getId())
                    .classroomDTO(ClassroomDTO.builder().id(classNotice.getClassroom().getId()).build())
                    .userDTO(UserDTO.builder().username(user.getUsername()).nickname(user.getNickname()).build())
                    .title(classNotice.getTitle())
                    .content(classNotice.getContent())
                    .classNoticeType(classNotice.getClassNoticeType())
                    .build();

            return classNoticeDTO;
        } catch(Exception e){
            log.info("Failed e : " + e.getMessage());
        }
        return null;
    }

}

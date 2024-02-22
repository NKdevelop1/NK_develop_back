package com.nkedu.back.serviceImpl;

import com.nkedu.back.api.ClassroomService;
import com.nkedu.back.dto.ClassroomDTO;
import com.nkedu.back.entity.Classroom;

import com.nkedu.back.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    /**
     * Classroom 엔티티의 CRUD 관련 API 구현 코드입니다.
     * @author beom-i
     */

    @Override
    public boolean createClassroom(ClassroomDTO classroomDTO) {
        try{
            if (!ObjectUtils.isEmpty(classroomRepository.findOneById(classroomDTO.getId()))) {
                throw new RuntimeException("이미 등록된 수업입니다.");
            }
            Classroom classroom = (Classroom) Classroom.builder()
                    .classname(classroomDTO.getClassname())
                    .build();
            classroomRepository.save(classroom);
            return true;
        } catch(Exception e) {
            log.error("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            classroomRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.info("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean updateClassroom(Long id, ClassroomDTO classroomDTO) {
        try {
            Classroom searchedClassroom = classroomRepository.findOneById(id).get();

            if(ObjectUtils.isEmpty(searchedClassroom))
                return false;

            if(!ObjectUtils.isEmpty(classroomDTO.getClassname()))
                searchedClassroom.setClassname(classroomDTO.getClassname());

            classroomRepository.save(searchedClassroom);

            return true;
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<ClassroomDTO> getClassrooms() {
        try {
            List<ClassroomDTO> classroomDTOs = new ArrayList<>();

            List<Classroom> classrooms = classroomRepository.findAll();

            for(Classroom classroom : classrooms) {
                ClassroomDTO classroomDTO = new ClassroomDTO();
                classroomDTO.setId(classroom.getId());
                classroomDTO.setClassname(classroom.getClassname());

                classroomDTOs.add(classroomDTO);
            }
            return classroomDTOs;
        } catch(Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }

        return null;
    }

    @Override
    public ClassroomDTO findById(Long id) {
        try{
            Classroom classroom = classroomRepository.findOneById(id).get();

            ClassroomDTO classroomDTO = new ClassroomDTO();
            classroomDTO.setId(classroom.getId());
            classroomDTO.setClassname(classroom.getClassname());

            return classroomDTO;
        } catch(Exception e){
            log.info("Failed e : " + e.getMessage());
        }
        return null;
    }
}

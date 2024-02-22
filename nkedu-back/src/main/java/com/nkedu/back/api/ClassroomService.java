package com.nkedu.back.api;

import com.nkedu.back.dto.ClassroomDTO;

import java.util.List;

public interface ClassroomService {

    /**
     * 수업 생성
     * @param classroomDTO
     * @return Classroom
     * @author beom-i
     */
    public boolean createClassroom(ClassroomDTO classroomDTO);

    /**
     * 수업 삭제
     * @param id
     * @author beom-i
     */
    public boolean deleteById(Long id);

    /**
     * 수업 설정 (param = 바꾸고 싶은 classroom의 id, 바꿀 classroomDTO)
     * @param id, classroomDTO
     * @return boolean
     * @author beom-i
     */
    public boolean updateClassroom(Long id, ClassroomDTO classroomDTO);

    /**
     * 모든 수업 리스트 조회
     * @return List<Classroom>
     * @author beom-i
     */
    public List<ClassroomDTO> getClassrooms();

    /**
     * 특정 수업 조회
     * @param id
     * @return ClassroomDTO
     * @author beom-i
     */
    public ClassroomDTO findById(Long id);
}

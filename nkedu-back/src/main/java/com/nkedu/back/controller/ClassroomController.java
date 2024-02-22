package com.nkedu.back.controller;

import com.nkedu.back.api.ClassroomService;
import com.nkedu.back.dto.ClassroomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ClassroomController {

    private final ClassroomService classroomService;

    /**
     * 모든 수업을 조회하는 controller 입니다.
     * @return List<ClassroomDTO>
     * @author beom-i
     */
    @GetMapping("/classroom")
    public ResponseEntity<List<ClassroomDTO>> getClassrooms() {
        List<ClassroomDTO> classroomDTOs = classroomService.getClassrooms();

        if (classroomDTOs != null) {
            return new ResponseEntity<>(classroomDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 id의 수업을 조회하는 controller 입니다.
     * @param id
     * @return ClassroomDTO
     * @author beom-i
     */
    @GetMapping("/classroom/{id}")
    public ResponseEntity<ClassroomDTO> getClassroom(@PathVariable("id") Long id) {

        ClassroomDTO classroomDTO = classroomService.findById(id);

        if (classroomDTO != null) {
            return new ResponseEntity<>(classroomDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 id의 수업을 설정하는 controller 입니다.
     * @param id, classroomDTO
     *
     * @author beom-i
     */
    @PutMapping("/classroom/{id}")
    public ResponseEntity<Void> updateClassroom(@PathVariable("id") Long id, @RequestBody ClassroomDTO classroomDTO) {

        boolean result = classroomService.updateClassroom(id, classroomDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 수업을 생성하는 controller 입니다.
     * @param classroomDTO
     *
     * @author beom-i
     */
    @PostMapping("/classroom")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createClassroom(@Validated @RequestBody ClassroomDTO classroomDTO) {

        boolean result = classroomService.createClassroom(classroomDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 수업을 삭제하는 controller 입니다.
     * @param id
     *
     * @author beom-i
     */
    @DeleteMapping("/classroom/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClassroom(@PathVariable("id") Long id) {

        boolean result = classroomService.deleteById(id);

        if (result == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

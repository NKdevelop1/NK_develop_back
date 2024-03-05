package com.nkedu.back.controller;

import com.nkedu.back.api.ClassNoticeService;
import com.nkedu.back.dto.ClassNoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ClassNoticeController {
    private final ClassNoticeService classNoticeService;


    /**
     * 수업 공지를 생성하는 controller 입니다.
     * @param classNoticeDTO
     *
     * @author beom-i
     */
    @PostMapping("/class-notice")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createClassNotice(@Validated @RequestBody ClassNoticeDTO classNoticeDTO) {

        boolean result = classNoticeService.createClassNotice(classNoticeDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 id의 수업 공지를 수정하는 controller 입니다.
     * (제목, 내용, 공지 공개 범위만 수정할 수 있습니다.)
     * @param id, classNoticeDTO
     * @author beom-i
     */
    @PutMapping("/class-notice/{id}")
    public ResponseEntity<Void> updateClassNotice(@PathVariable("id") Long id, @RequestBody ClassNoticeDTO classNoticeDTO) {

        boolean result = classNoticeService.updateClassNotice(id, classNoticeDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 수업 공지를 삭제하는 controller 입니다.
     * @param id
     *
     * @author beom-i
     */
    @DeleteMapping("/class-notice/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClassNotice(@PathVariable("id") Long id) {

        boolean result = classNoticeService.deleteClassNoticeById(id);

        if (result == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

package com.nkedu.back.controller;

import com.nkedu.back.api.NoticeService;
import com.nkedu.back.dto.ClassroomDTO;
import com.nkedu.back.dto.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class NoticeController {
    private final NoticeService noticeService;


    /**
     * 특정 공지를 생성하는 controller 입니다.
     * @param noticeDTO
     *
     * @author beom-i
     */
    @PostMapping("/notice")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createNotice(@Validated @RequestBody NoticeDTO noticeDTO) {

        boolean result = noticeService.createNotice(noticeDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 공지를 조회하는 controller 입니다.
     * @return List<NoticeDTO>
     * @author beom-i
     */
    @GetMapping("/notice")
    public ResponseEntity<List<NoticeDTO>> getNotices() {
        List<NoticeDTO> noticeDTOs = noticeService.getNotices();

        if (noticeDTOs != null) {
            return new ResponseEntity<>(noticeDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 id의 공지을 조회하는 controller 입니다.
     * @param id
     * @return NoticeDTO
     * @author beom-i
     */
    @GetMapping("/notice/{id}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable("id") Long id) {

        NoticeDTO noticeDTO = noticeService.getNoticeById(id);

        if (noticeDTO != null) {
            return new ResponseEntity<>(noticeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 id의 공지를 수정하는 controller 입니다.
     * (제목, 내용, 공지 공개 범위만 수정할 수 있습니다.)
     * @param id, noticeDTO
     * @author beom-i
     */
    @PutMapping("/notice/{id}")
    public ResponseEntity<Void> updateNotice(@PathVariable("id") Long id, @RequestBody NoticeDTO noticeDTO) {

        boolean result = noticeService.updateNotice(id, noticeDTO);

        if (result == true) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 특정 공지를 삭제하는 controller 입니다.
     * @param id
     *
     * @author beom-i
     */
    @DeleteMapping("/notice/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteNotice(@PathVariable("id") Long id) {

        boolean result = noticeService.deleteNoticeById(id);

        if (result == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

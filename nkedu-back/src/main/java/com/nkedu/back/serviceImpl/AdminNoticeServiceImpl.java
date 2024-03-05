package com.nkedu.back.serviceImpl;

import com.nkedu.back.api.AdminNoticeService;
import com.nkedu.back.dto.AdminDTO;
import com.nkedu.back.dto.AdminNoticeDTO;
import com.nkedu.back.entity.Admin;
import com.nkedu.back.entity.AdminNotice;
import com.nkedu.back.entity.AdminNotice.AdminNoticeType;
import com.nkedu.back.repository.AdminNoticeRepository;
import com.nkedu.back.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminNoticeServiceImpl implements AdminNoticeService {
    private final AdminNoticeRepository adminNoticeRepository;
    private final AdminRepository adminRepository;

    /**
     * ClassNotice 엔티티의 CRUD 관련 API 구현 코드입니다.
     * @author beom-i
     */

    @Override
    public boolean createAdminNotice(AdminNoticeDTO adminNoticeDTO) {
        try{
            Long admin_id = adminNoticeDTO.getAdminDTO().getId();
            AdminNoticeType adminNoticeType = adminNoticeDTO.getAdminNoticeType();

            // Q. noticeDTO로 ID를 받지 않는데 어떻게 중복 검증을 할 수 있는가?
            if (!ObjectUtils.isEmpty(adminNoticeRepository.findOneById(adminNoticeDTO.getId()))) {
                throw new RuntimeException("이미 등록된 공지입니다.");
            }
            AdminNotice adminNotice = AdminNotice.builder()
                    .admin(adminRepository.findOneById(admin_id).get())
                    .title(adminNoticeDTO.getTitle())
                    .content(adminNoticeDTO.getContent())
                    .adminNoticeType(adminNoticeType)
                    .created(new Timestamp(System.currentTimeMillis()))
                    .build();

            adminNoticeRepository.save(adminNotice);
            return true;
        } catch(Exception e) {
            log.error("Failed: " + e.getMessage(),e);
        }
        return false;    }

    @Override
    public boolean deleteAdminNoticeById(Long id) {
        try{
            adminNoticeRepository.deleteById(id);
            return true;
        } catch (Exception e){
            log.info("Failed: " + e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean updateAdminNotice(Long id, AdminNoticeDTO adminNoticeDTO) {
        try{
            AdminNotice searchedAdminNotice = adminNoticeRepository.findOneById(id).get();

            if(ObjectUtils.isEmpty(searchedAdminNotice))
                return false;

            if(!ObjectUtils.isEmpty(adminNoticeDTO.getTitle()))
                searchedAdminNotice.setTitle(adminNoticeDTO.getTitle());
            if(!ObjectUtils.isEmpty(adminNoticeDTO.getContent()))
                searchedAdminNotice.setContent(adminNoticeDTO.getContent());
            if(!ObjectUtils.isEmpty(adminNoticeDTO.getAdminNoticeType()))
                searchedAdminNotice.setAdminNoticeType(adminNoticeDTO.getAdminNoticeType());

            adminNoticeRepository.save(searchedAdminNotice);
            return true;
        } catch(Exception e){
            log.info("Failed e : " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<AdminNoticeDTO> getAdminNotices(String adminNoticeType) {
        try{

            List<AdminNoticeDTO> adminNoticeDTOs = new ArrayList<>();
            List<AdminNotice> adminNotices = null;

            if(adminNoticeType == null) {
                adminNotices = adminNoticeRepository.findAll();
            }
            else if( adminNoticeType.equals("student")){
                List<AdminNoticeType> types = Arrays.asList(AdminNoticeType.STUDENT, AdminNoticeType.STUDENT_PARENT, AdminNoticeType.STUDENT_TEACHER,AdminNoticeType.ENTIRE);
                adminNotices = adminNoticeRepository.findByAdminNoticeTypes(types).get();

            }
            else if(adminNoticeType.equals("parent")){
                List<AdminNoticeType> types = Arrays.asList(AdminNoticeType.PARENT, AdminNoticeType.PARENT_TEACHER, AdminNoticeType.STUDENT_PARENT,AdminNoticeType.ENTIRE);
                adminNotices = adminNoticeRepository.findByAdminNoticeTypes(types).get();
            }
            else if(adminNoticeType.equals("teacher")){
                List<AdminNoticeType> types = Arrays.asList(AdminNoticeType.TEACHER, AdminNoticeType.STUDENT_TEACHER, AdminNoticeType.PARENT_TEACHER,AdminNoticeType.ENTIRE);
                adminNotices = adminNoticeRepository.findByAdminNoticeTypes(types).get();
            }
            else if(adminNoticeType.equals("admin")){
                adminNotices = adminNoticeRepository.findAll();
            }

            for(AdminNotice adminNotice : adminNotices){

                Admin admin = adminRepository.findOneById(adminNotice.getAdmin().getId()).get();
                if(ObjectUtils.isEmpty(admin)){
                    throw new RuntimeException("공지를 작성한 admin이 존재하지 않음");
                }

                AdminNoticeDTO adminNoticeDTO = AdminNoticeDTO.builder()
                        .id(adminNotice.getId())
                        .adminDTO(AdminDTO.builder().id(adminNotice.getAdmin().getId()).build())
                        .title(adminNotice.getTitle())
                        .content(adminNotice.getContent())
                        .adminNoticeType(adminNotice.getAdminNoticeType())
                        .build();
                adminNoticeDTOs.add(adminNoticeDTO);
            }
            return adminNoticeDTOs;
        }catch (Exception e){
            log.info("Failed : "+e.getMessage());
        }
        return null;
    }



    @Override
    public AdminNoticeDTO getAdminNotice(Long adminNotice_id) {
        try{
            AdminNotice adminNotice = adminNoticeRepository.findOneById(adminNotice_id).get();

            AdminNoticeDTO adminNoticeDTO = AdminNoticeDTO.builder()
                    .id(adminNotice.getId())
                    .adminDTO(AdminDTO.builder().id(adminNotice.getAdmin().getId()).build())
                    .title(adminNotice.getTitle())
                    .content(adminNotice.getContent())
                    .adminNoticeType(adminNotice.getAdminNoticeType())
                    .build();
            return adminNoticeDTO;
        } catch(Exception e){
            log.info("Failed : " + e.getMessage());
        }
        return null;
    }
}

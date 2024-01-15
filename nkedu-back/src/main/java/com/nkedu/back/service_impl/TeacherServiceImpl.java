package com.nkedu.back.service_impl;

import java.security.Key;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nkedu.back.api.TeacherService;
import com.nkedu.back.model.Teacher;
import com.nkedu.back.model.TeacherDto;
import com.nkedu.back.repository.TeacherRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{

	private final TeacherRepository teacherRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    // jwtSecret을 통해 secretKey를 생성
    private byte[] secretKey;

    // secretKey로 Key 객체 생성
    private Key key;

    @PostConstruct
    public void init() {
        this.secretKey = jwtSecret.getBytes();

        // 키의 길이가 256비트 이하라면 보안상 취약하므로 적절한 길이로 변경
        if (secretKey.length < 32) {
            throw new WeakKeyException("Key length must be at least 256 bits.");
        }
        
        this.key = Keys.hmacShaKeyFor(secretKey);
    }

	@Override
	public boolean createTeacher(TeacherDto teacherDto) {
		try {
			Teacher teacher = new Teacher();
			teacher.setUserId(teacherDto.getUserId());
			teacher.setUserPw(teacherDto.getUserPw());
			teacher.setName(teacherDto.getName());
			teacher.setBirth(teacherDto.getBirth());
			teacher.setPhoneNumber(teacherDto.getPhoneNumber());
			teacher.setCreated(new Timestamp(System.currentTimeMillis()));;
			
			teacherRepository.save(teacher);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteTeacherById(@NonNull Long id) {
		try {
			teacherRepository.deleteById(id);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateTeacher(@NonNull Long id, TeacherDto teacherDto) {
		try {
			Teacher searchedTeacher = teacherRepository.findById(id).get();
			
			if(ObjectUtils.isEmpty(searchedTeacher))
				return false;
			
			if(!ObjectUtils.isEmpty(teacherDto.getUserId()))
				searchedTeacher.setUserId(teacherDto.getUserId());
			if(!ObjectUtils.isEmpty(teacherDto.getUserPw()))
				searchedTeacher.setUserPw(teacherDto.getUserPw());
			if(!ObjectUtils.isEmpty(teacherDto.getName()))
				searchedTeacher.setName(teacherDto.getName());
			if(!ObjectUtils.isEmpty(teacherDto.getPhoneNumber()))
				searchedTeacher.setPhoneNumber(teacherDto.getPhoneNumber());	
			if(!ObjectUtils.isEmpty(teacherDto.getBirth()))
				searchedTeacher.setBirth(teacherDto.getBirth());
			
			teacherRepository.save(searchedTeacher);
			
			return true;
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}
		
		return false;
	}

	@Override
	public List<TeacherDto> getTeachers() {
		try {
			List<TeacherDto> teacherDtos = new ArrayList<>();
			
			List<Teacher> teachers = teacherRepository.findAll();
			
			for(Teacher teacher : teachers) {
				TeacherDto teacherDto = new TeacherDto();
				teacherDto.setId(teacher.getId());
				teacherDto.setUserId(teacher.getUserId());
				teacherDto.setName(teacher.getName());
				teacherDto.setPhoneNumber(teacher.getPhoneNumber());
				teacherDto.setBirth(teacher.getBirth());
				
				teacherDtos.add(teacherDto);
			}
			
			return teacherDtos;
		} catch(Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

	@Override
	public TeacherDto getTeacherById(@NonNull Long id) {
		
		try {
			Teacher teacher = teacherRepository.findById(id).get();
			
			TeacherDto teacherDto = new TeacherDto();
			teacherDto.setId(teacher.getId());
			teacherDto.setUserId(teacher.getUserId());
			teacherDto.setName(teacher.getName());
			teacherDto.setPhoneNumber(teacher.getPhoneNumber());
			teacherDto.setBirth(teacher.getBirth());
			
			return teacherDto;
			
		} catch (Exception e) {
			log.info("[Failed] e : " + e.getMessage());
		}

		return null;
	}

    @Override
    public String login(TeacherDto teacherDto) {
        try {
            Teacher teacher = teacherRepository.findByUserId(teacherDto.getUserId()).orElse(null);

            if (!ObjectUtils.isEmpty(teacher) && teacher.getUserPw().equals(teacherDto.getUserPw())) {
                // 로그인 성공 시 토큰 발급
                return generateJwtToken(teacher.getId(), teacher.getUserId());
            }
        } catch (Exception e) {
            log.info("[Failed] e : " + e.getMessage());
        }
        return null;
    }

     // JWT 토큰 생성 메소드
     private String generateJwtToken(Long teacherId, String userId) {
        return Jwts
                .builder()
                .setSubject(userId)
                .claim("teacherId", teacherId)
                .signWith(key ,SignatureAlgorithm.HS256)
                .compact();
    }    

}

    // public Teacher login(Teacher teacher) {
    //     noTeacherByUserId(teacher);
    //     noTeacherByPassword(teacher);

    //     String token = securityService.createToken(teacher.getUserId(), 2*1000*60);
    //     teacher.setJwt(token);
    //     return teacher;
    // }

    // private void NoTeacherByUserId(Teacher teacher) {
    //     if (teacherRepository.findByUserId(teacher.getUserId()).isEmpty()) {
    //         throw new IllegalStateException("아이디가 존재하지 않습니다.");
    //     }
    // }

    // private void NoTeacherByPassword(Teacher teacher) {
    //     Optional<Object> optoinalTeacher = teacherRepository.findByUserId(teacher.getUserId());
    //     if (optionalTeacher.isPresent()) {
    //         Teacher teacher = (Teacher) optionalTeacher.get();
    //         if (!teacher.getUserPw().equals(teacher.getUserPw())) {
    //             throw new IllegalStateException("비밀번호가 틀립니다.");
    //         }
    //     }
    // }
// }
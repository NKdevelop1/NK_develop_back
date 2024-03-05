package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.ClassNotice.ClassNoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassNoticeDTO {

    private Long id;

    @JsonProperty(value="teacher")
    private TeacherDTO teacherDTO;

    @JsonProperty(value="classroom")
    private ClassroomDTO classroomDTO;

    private String title;

    private String content;

    private ClassNoticeType classNoticeType;
}

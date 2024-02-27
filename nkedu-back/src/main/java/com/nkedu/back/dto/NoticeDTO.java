package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.Notice.NoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticeDTO {

    private Long id;

    @JsonProperty(value="user")
    private UserDTO userDTO;

    @JsonProperty(value="classroom")
    private ClassroomDTO classroomDTO;

    private String title;

    private String content;

    private NoticeType noticeType;
}

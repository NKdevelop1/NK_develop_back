package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.AdminNotice.AdminNoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminNoticeDTO {
    private Long id;

    @JsonProperty(value="admin")
    private AdminDTO adminDTO;

    private String title;

    private String content;

    private AdminNoticeType adminNoticeType;
}

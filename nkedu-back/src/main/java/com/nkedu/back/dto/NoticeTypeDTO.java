package com.nkedu.back.dto;

import com.nkedu.back.entity.Notice.NoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeTypeDTO {
    private NoticeType noticeType;

}

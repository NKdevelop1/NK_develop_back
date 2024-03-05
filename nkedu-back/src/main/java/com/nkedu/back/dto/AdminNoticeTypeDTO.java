package com.nkedu.back.dto;

import com.nkedu.back.entity.AdminNotice.AdminNoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminNoticeTypeDTO {
    private AdminNoticeType adminNoticeType;
}

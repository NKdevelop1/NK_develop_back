package com.nkedu.back.dto;

import com.nkedu.back.entity.ClassNotice.ClassNoticeType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassNoticeTypeDTO {
    private ClassNoticeType classNoticeType;
}

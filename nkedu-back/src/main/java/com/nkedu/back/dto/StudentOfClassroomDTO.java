package com.nkedu.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nkedu.back.entity.Classroom;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentOfClassroomDTO {

    @JsonProperty(value="classroom")
    private ClassroomDTO classroomDTO;

    @JsonProperty(value="student")
    private StudentDTO studentDTO;
}


package com.nkedu.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Table(name="class_otice")
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassNotice {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Column(name="title", length=100, nullable=false)
    private String title;

    @Column(name="content", length=1000, nullable=false)
    private String content;


    @JsonIgnore
    @Column(name="created", nullable=false)
    private Timestamp created;

    @Enumerated(EnumType.STRING)
    @Column(name="classNotice_type",nullable = false)
    private ClassNoticeType classNoticeType;


    /**
     * 선생님 공지 선택지 - STUDENT PARENT ENTIRE
     * */
    public enum ClassNoticeType {
        STUDENT,
        PARENT,
        ENTIRE
    }
}

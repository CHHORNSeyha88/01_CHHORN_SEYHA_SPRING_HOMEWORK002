package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course;

import lombok.Data;

@Data
public class CourseRequest {
    private String courseName;
    private String description;
    private Integer instructorId;
}

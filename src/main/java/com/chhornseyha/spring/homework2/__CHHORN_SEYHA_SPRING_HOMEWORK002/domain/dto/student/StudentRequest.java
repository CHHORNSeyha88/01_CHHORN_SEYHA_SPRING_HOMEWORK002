package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentRequest {
    private String studentName;
    @Email
    private String email;
    private String phoneNumber;
    private List<Integer> coursesId;
}

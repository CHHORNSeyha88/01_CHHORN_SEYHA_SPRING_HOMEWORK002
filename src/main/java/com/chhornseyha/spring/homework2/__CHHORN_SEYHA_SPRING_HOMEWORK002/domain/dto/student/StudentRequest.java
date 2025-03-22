package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentRequest {
    private String studentName;
    @Schema(example = "example@gmail.com")
    @Email
    private String email;
    private String phoneNumber;
    @Schema(example = "[0]")
    private List<Integer> coursesId;
}

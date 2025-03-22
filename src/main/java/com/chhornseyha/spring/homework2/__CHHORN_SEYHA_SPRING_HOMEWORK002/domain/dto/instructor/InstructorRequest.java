package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.instructor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorRequest {
    private String instructorName;
    @Schema(example = "example@gmail.com")
    @Email
    private String email;
}

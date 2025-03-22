package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.controller;


import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.MessageConstant;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.httpresponse.ApiResponse;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.instructor.InstructorRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl.InstructorsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructor")
public class InstructorsController {
    private final InstructorsServiceImpl instructorsService;

    @PostMapping

    public ResponseEntity<ApiResponse<Instructor>> addInstructor(@RequestBody InstructorRequest instructorsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Instructor>builder()
                        .message(MessageConstant.Instructor.CREATES_SUCCESSFULLY)
                        .payload(instructorsService.insertInstructor(instructorsRequest))
                        .status(HttpStatus.CREATED)
                        .build()
        );

    }
    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Integer id) {
        Instructor instructor = instructorsService.selectInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Instructor>builder()
                            .message("Course with ID " + id + " not found")
                            .payload(null)
                            .status(HttpStatus.NOT_FOUND)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .message(MessageConstant.Instructor.GET_INSTRUCTOR_BY_ID_SUCCESSFULLY)
                        .payload(instructorsService.selectInstructorById(id))
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable("instructor-id") Integer id, @RequestBody InstructorRequest instructorRequest) {
        Instructor instructor = instructorsService.selectInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Instructor>builder()
                            .message("Course with ID " + id + " not found")
                            .payload(null)
                            .status(HttpStatus.NOT_FOUND)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .message(MessageConstant.Instructor.UPDATED_SUCCESSFULLY)
                        .payload(instructorsService.updateInstructor(id, instructorRequest))
                        .status(HttpStatus.OK)
                        .build()
        );
    };
    @GetMapping
    public ApiResponse<List<Instructor>> getAllInstructors(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1")Integer page) {
        List<Instructor> instructors = instructorsService.getAllInstructors(size,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Instructor>>builder()
                        .message(MessageConstant.Instructor.GET_ALL_INSTRUCTOR_SUCCESSFULLY)
                        .payload(instructors)
                        .status(HttpStatus.OK)
                        .build()
        ).getBody();
    }
    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable("instructor-id") Integer id) {
        Instructor instructor = instructorsService.selectInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<Instructor>builder()
                            .message("Course with ID " + id + " not found")
                            .payload(null)
                            .status(HttpStatus.NOT_FOUND)
                            .build());
        }
        instructorsService.deleteInstructor(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .message(MessageConstant.Instructor.DELETED_SUCCESSFULLY)
                        .payload(null)
                        .status(HttpStatus.OK)
                        .build()
        );
    }

}

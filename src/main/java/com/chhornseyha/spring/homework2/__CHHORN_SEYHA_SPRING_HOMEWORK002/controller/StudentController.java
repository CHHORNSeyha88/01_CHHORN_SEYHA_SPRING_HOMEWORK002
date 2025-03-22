package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.controller;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.MessageConstant;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.httpresponse.ApiResponse;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student.StudentRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Student;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .message(MessageConstant.Student.CREATES_SUCCESSFULLY)
                        .payload(studentServiceImpl.createStudent(studentRequest))
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@RequestBody StudentRequest studentRequest , @PathVariable("student-id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .message(MessageConstant.Student.UPDATED_SUCCESSFULLY)
                        .payload(studentServiceImpl.updateStudentById(id, studentRequest))
                        .status(HttpStatus.CREATED)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Student> studentsList = studentServiceImpl.getAllStudents(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Student>>builder()
                        .message(MessageConstant.Student.GET_ALL_STUDENT_SUCCESSFULLY)
                        .payload(studentsList)
                        .status(HttpStatus.OK)
                        .build()
        );
    }
    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .message(MessageConstant.Student.GET_STUDENT_BY_ID_SUCCESSFULLY)
                        .payload(studentServiceImpl.getStudentById(id))
                        .status(HttpStatus.OK)
                        .build()

        );
    }
    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable("student-id") Integer id) {
        studentServiceImpl.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .message(MessageConstant.Student.DELETED_SUCCESSFULLY)
                        .payload(null)
                        .status(HttpStatus.OK)
                        .build()
        );

    }

}

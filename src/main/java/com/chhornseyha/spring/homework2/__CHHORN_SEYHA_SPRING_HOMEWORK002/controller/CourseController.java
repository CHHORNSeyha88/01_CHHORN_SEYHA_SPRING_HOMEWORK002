package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.controller;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.MessageConstant;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.constant.httpresponse.ApiResponse;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseUpdateRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Course;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    @GetMapping
    public ApiResponse<List<Course>> getCourse(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1") Integer page) {
        List<Course> coursesList = courseService.findAll(size,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Course>>builder()
                        .message(MessageConstant.Course.GET_ALL_COURSE_SUCCESSFULLY)
                        .payload(coursesList)
                        .status(HttpStatus.OK)
                        .build()
        ).getBody();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Course>builder()
                        .message(MessageConstant.Course.GET_COURSE_BY_ID_SUCCESSFULLY)
                        .payload(courseService.findById(id))
                        .status(HttpStatus.OK)
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Course>builder()
                        .message(MessageConstant.Course.CREATES_SUCCESSFULLY)
                        .payload(courseService.addCourse(courseRequest))
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Course>builder()
                        .message(MessageConstant.Course.DELETED_SUCCESSFULLY)
                        .payload(null)
                        .status(HttpStatus.OK)
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable int id, @RequestBody CourseUpdateRequest courseUpdateRequest) {
       return ResponseEntity.status(HttpStatus.OK).body(
               ApiResponse.<Course>builder()
                       .message(MessageConstant.Course.UPDATED_SUCCESSFULLY)
                       .payload(courseService.updateCourse(courseUpdateRequest,id))
                       .status(HttpStatus.OK)
                       .build()
       );

    }

}

package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseUpdateRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Course;
import java.util.List;

public interface CourseService {
    Course addCourse(CourseRequest courseRequest);
    void deleteCourse(Integer courseId);
    Course updateCourse(CourseUpdateRequest courseUpdateRequest, int id);
    Course findById(int id);
     List<Course> findAll(Integer size,Integer page);


}

package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseUpdateRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Course;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository.CourseRepository;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
   @Override
    public List<Course> findAll(Integer size,Integer page) {
       return  courseRepository.getAllCourses(size,page);
    }

    @Override
    public Course findById(int id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public Course addCourse(CourseRequest courseRequest) {
        return courseRepository.insertionCourse(courseRequest);
    }

    @Override
    public void deleteCourse(Integer courseId) {
         courseRepository.deleteCourseById(courseId);
    }

    @Override
    public Course updateCourse(CourseUpdateRequest courseUpdateRequest, int id) {
        // Step 1: Validate the instructor_id
        int instructorCount = courseRepository.checkInstructorExists(courseUpdateRequest.getInstructorId());
        if (instructorCount == 0) {
            throw new IllegalArgumentException("Invalid instructorId: " + courseUpdateRequest.getInstructorId());
        }
        // Step 2: Perform the update
        int rowsAffected = courseRepository.updateCourse(courseUpdateRequest, id);
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Course with ID " + id + " does not exist");
        }
        // Step 3: Fetch and return the updated course
        return courseRepository.getCourseById(id);
    }


}

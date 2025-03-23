package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.course.CourseUpdateRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Course;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseRepository {


    @Select("""
    INSERT INTO course(course_name, description, instructor_id)
    VALUES (#{course.courseName}, #{course.description}, #{course.instructorId})
    RETURNING *;
""")
    @ResultMap("courseMapper")
    Course insertionCourse(@Param("course") CourseRequest courseRequest);

//-- Get All
@Select("""
    SELECT * FROM course
    offset #{size} * (#{page}-1)
    limit #{size};
""")
@Results(id = "courseMapper", value = {
        @Result(property = "courseId", column = "course_id"),
        @Result(property = "courseName", column = "course_name"),
        @Result(property = "description", column = "description"),
        @Result(property = "instructor", column = "instructor_id",
                one = @One(select = "com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository.InstructorsRepository.selectInstructorById"))
})
List<Course> getAllCourses(Integer size,Integer page);

    //-- Get By Id
    @Select("""
select * from course where course_id = #{courseId};
""")
    @ResultMap("courseMapper")
    Course getCourseById(int courseId);

    @Delete("""
DELETE from course where course_id = #{courseId};
""")
    void deleteCourseById(int courseId);
//    -- Validation Checking
    @Select("""
    SELECT COUNT(*) 
    FROM instructor 
    WHERE instructor_id = #{instructorId};
""")
    int checkInstructorExists(@Param("instructorId") int instructorId);
//    --Updated
    @Update("""
    UPDATE course 
    SET course_name = #{courseRq.courseName}, 
        description = #{courseRq.description},
        instructor_id = #{courseRq.instructorId} 
    WHERE course_id = #{courseId};
""")
    int updateCourse(@Param("courseRq") CourseUpdateRequest courseUpdateRequest, int courseId);

//    select course by student id for handle to Student
@Select("""
    SELECT c.* 
    FROM course c
    INNER JOIN student_course sc ON c.course_id = sc.course_id
    WHERE sc.student_id = #{studentId}
""")
@Results(id = "courseMapperForStudent", value = {
        @Result(property = "courseId", column = "course_id"),
        @Result(property = "courseName", column = "course_name"),
        @Result(property = "description", column = "description"),
        @Result(property = "instructor", column = "instructor_id",
                one = @One(select = "getInstructorById"))
})
List<Course> getCoursesByStudentId(@Param("studentId") Integer studentId);

    // -- handle for getCoursesByStudentId
    @Select("SELECT * FROM instructor WHERE instructor_id = #{instructorId}")
            @Result(property = "instructorId", column = "instructor_id")
            @Result(property = "instructorName", column = "instructor_name")
            @Result(property = "email", column = "email")
    Instructor getInstructorById(@Param("instructorId") Integer instructorId);
}

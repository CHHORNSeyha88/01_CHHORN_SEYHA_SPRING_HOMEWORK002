package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Student;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student.StudentRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentRepository {
//    --Select All
@Results(id = "studentMapper", value = {
        @Result(property = "studentId", column = "student_id"),
        @Result(property = "studentName", column = "student_name"),
        @Result(property = "email", column = "email"),
        @Result(property = "phoneNumber", column = "phone_number"),
        @Result(property = "courses", column = "student_id",
                many = @Many(select = "com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository.CourseRepository.getCoursesByStudentId"))
})
@Select("""
select * from student
offset #{size} * (#{page}-1)
limit #{size};
""")
List<Student> findAllStudents(Integer page, Integer size);

//-- get by id
    @Select("""
select * from student where student_id = #{studentId}
""")
    @ResultMap("studentMapper")
    Student findStudentById(int studentId);


    //-- Insert Student
    @Insert("""
        INSERT INTO student (student_name, email, phone_number)
        VALUES (#{student.studentName}, #{student.email}, #{student.phoneNumber})
        RETURNING student_id;
    """)
    Integer insertStudent(@Param("student") StudentRequest studentRequest);

    //-- Insert Single Student-Course Relationship
    @Insert("""
        INSERT INTO student_course (student_id, course_id)
        VALUES (#{studentId}, #{courseId});
    """)
    void insertStudentCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

//-- delete
    @Delete("""
   Delete from student where student_id = #{studentId}
""")
    void deleteStudentById(int studentId);

    //    --create new

    @Select("""
insert into student(student_name, email, phone_number)
values (#{student.studentName},#{student.email},#{student.phoneNumber})
returning student_id;

""")
    Integer insertNewStudent(@Param("student") StudentRequest studentRequest);

//    --update
    @Select("""
UPDATE student
SET student_name = #{student.studentName}, email = #{student.email}, phone_number = #{student.phoneNumber}
WHERE student_id = #{studentId}
returning student_id;

""")
    Integer updateStudent(@Param("student") StudentRequest studentRequest, Integer studentId);

}

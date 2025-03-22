package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.instructor.InstructorRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InstructorsRepository {

//-- insertion
    @Select("""
                insert into instructor(instructor_name, email)\s
                VALUES(#{instructor.instructorName}, #{instructor.email})
                returning *;

            """)
    @ResultMap("instructorMapper")
    Instructor insertInstructors(@Param("instructor") InstructorRequest instructorRequest);

    //-- update
    @Update("""
       UPDATE instructor
       SET instructor_name = #{instructor.instructorName}, email = #{instructor.email}
       where instructor_id = #{instructor.updateId}
       returning *;

""")
    @ResultMap("instructorMapper")
    Instructor updateInstructors(@Param("instructor") InstructorRequest instructorRequest, Integer updateId);


    //-- find by Id
    @Select("""
    SELECT * FROM instructor WHERE instructor_id = #{instructorId}
""")
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    Instructor selectInstructorById(@Param("instructorId") Integer id);

//-- select all
    @Select("""
      select * from instructor
      offset #{size}*(#{page}-1)
      limit #{size};
""")
    @ResultMap("instructorMapper")
    List<Instructor> selectAllInstructors(Integer size, Integer page);

    //-- delete
    @Delete("""
DELETE FROM instructor where instructor_id = #{instructorId}
""")
    void deleteInstructorById(@Param("instructorId") Integer id);

}

package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.instructor.InstructorRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;

import java.util.List;

public interface InstructorsService {

    Instructor insertInstructor(InstructorRequest instructorRequest);
    Instructor selectInstructorById(Integer id);
    Instructor updateInstructor(Integer id, InstructorRequest instructorRequest);
    void deleteInstructor(Integer id);
    List<Instructor> getAllInstructors(Integer size,Integer page);


}

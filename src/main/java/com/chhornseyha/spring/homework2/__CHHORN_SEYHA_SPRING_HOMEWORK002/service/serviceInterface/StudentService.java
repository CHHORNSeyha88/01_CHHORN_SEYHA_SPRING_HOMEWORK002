package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Student;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student.StudentRequest;

import java.util.List;

public interface StudentService {
    Student createStudent (StudentRequest studentRequest);
    List<Student> getAllStudents(Integer page, Integer size);
    void deleteStudentById(int studentId);
    Student getStudentById(int studentId);
    Student updateStudentById(int studentId, StudentRequest studentRequest);


}

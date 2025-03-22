package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Student;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.student.StudentRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository.StudentRepository;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(StudentRequest studentRequest) {

        Integer studentId = studentRepository.insertNewStudent(studentRequest);

        for(Integer coursesId : studentRequest.getCoursesId())
        {
            studentRepository.insertStudentCourse(studentId, coursesId);
        }
 return studentRepository.findStudentById(studentId);
    }

    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        return studentRepository.findAllStudents(page, size);
    }

    @Override
    public void deleteStudentById(int studentId) {
         studentRepository.deleteStudentById(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public Student updateStudentById(int studentId, StudentRequest studentRequest) {
        Integer updateStudentId = studentRepository.updateStudent(studentRequest, studentId);

        for(Integer coursesId : studentRequest.getCoursesId())
        {
            studentRepository.insertStudentCourse(updateStudentId, coursesId);
        }
        return studentRepository.findStudentById(studentId);
    }


}

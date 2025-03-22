package com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceImpl;

import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.dto.instructor.InstructorRequest;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.domain.model.Instructor;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.repository.InstructorsRepository;
import com.chhornseyha.spring.homework2.__CHHORN_SEYHA_SPRING_HOMEWORK002.service.serviceInterface.InstructorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorsServiceImpl implements InstructorsService {
    private final InstructorsRepository instructorsRepository;
    @Override

    public Instructor insertInstructor(InstructorRequest instructorRequest) {
        return instructorsRepository.insertInstructors(instructorRequest);
    }

    @Override
    public Instructor selectInstructorById(Integer id) {
        return instructorsRepository.selectInstructorById(id);
    }

    @Override
    public Instructor updateInstructor(Integer id, InstructorRequest instructorRequest) {
        return instructorsRepository.updateInstructors(instructorRequest,id);
    }

    @Override
    public void deleteInstructor(Integer id) {
        instructorsRepository.deleteInstructorById(id);
    }

    @Override
    public List<Instructor> getAllInstructors(Integer size,Integer page) {
        return instructorsRepository.selectAllInstructors(size,page);
    }
}

package com.mitocode.exam.service.impl;

import com.mitocode.exam.model.Student;
import com.mitocode.exam.repository.IGenericRepository;
import com.mitocode.exam.repository.IStudentRepository;
import com.mitocode.exam.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepository repository;
    @Override
    protected IGenericRepository<Student, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<Student> getStudentsOrderByAgeDesc() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());
    }
}

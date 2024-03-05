package com.mitocode.exam.service.impl;

import com.mitocode.exam.model.Course;
import com.mitocode.exam.repository.ICourseRepository;
import com.mitocode.exam.repository.IGenericRepository;
import com.mitocode.exam.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    private final ICourseRepository repository;
    @Override
    protected IGenericRepository<Course, Integer> getRepository() {
        return repository;
    }
}

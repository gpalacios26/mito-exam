package com.mitocode.exam.service;

import com.mitocode.exam.model.Course;
import com.mitocode.exam.repository.ICourseRepository;
import com.mitocode.exam.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CourseServiceTest {

    @MockBean
    private CourseServiceImpl service;

    @MockBean
    private ICourseRepository repository;

    private Course COURSE_1;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.service = new CourseServiceImpl(repository);

        COURSE_1 = new Course(1, "Matemática", "MAT", true);
        Course COURSE_2 = new Course(2, "Lenguaje", "LEN", true);

        List<Course> courses = List.of(COURSE_1, COURSE_2);

        Mockito.when(repository.findAll()).thenReturn(courses);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(COURSE_1));
        Mockito.when(repository.save(any())).thenReturn(COURSE_1);
    }

    @Test
    void save() throws Exception{
        Course response = service.save(COURSE_1);
        assertNotNull(response);
    }

    @Test
    void update() throws Exception{
        Course response = service.update(COURSE_1, COURSE_1.getIdCourse());
        assertNotNull(response);
    }

    @Test
    void readAll() throws Exception{
        List<Course> response = service.readAll();
        assertNotNull(response);
        assertEquals(response.size(), 2);
    }

    @Test
    void readById() throws Exception{
        Course response = service.readById(COURSE_1.getIdCourse());
        assertNotNull(response);
    }

    @Test
    void delete() throws Exception{
        service.delete(COURSE_1.getIdCourse());
        verify (repository, times(1)).deleteById(COURSE_1.getIdCourse());
    }
}
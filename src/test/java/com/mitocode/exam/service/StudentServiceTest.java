package com.mitocode.exam.service;

import com.mitocode.exam.model.Student;
import com.mitocode.exam.repository.IStudentRepository;
import com.mitocode.exam.service.impl.StudentServiceImpl;
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
class StudentServiceTest {

    @MockBean
    private StudentServiceImpl service;

    @MockBean
    private IStudentRepository repository;

    private Student STUDENT_1;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.service = new StudentServiceImpl(repository);

        STUDENT_1 = new Student(1, "Gregory", "Palacios", "47896514", 32);
        Student STUDENT_2 = new Student(2, "Ernesto", "Medina", "58745869", 24);

        List<Student> students = List.of(STUDENT_1, STUDENT_2);

        Mockito.when(repository.findAll()).thenReturn(students);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(STUDENT_1));
        Mockito.when(repository.save(any())).thenReturn(STUDENT_1);
    }

    @Test
    void save() throws Exception{
        Student response = service.save(STUDENT_1);
        assertNotNull(response);
    }

    @Test
    void update() throws Exception{
        Student response = service.update(STUDENT_1, STUDENT_1.getIdStudent());
        assertNotNull(response);
    }

    @Test
    void readAll() throws Exception{
        List<Student> response = service.readAll();
        assertNotNull(response);
        assertEquals(response.size(), 2);
    }

    @Test
    void readById() throws Exception{
        Student response = service.readById(STUDENT_1.getIdStudent());
        assertNotNull(response);
    }

    @Test
    void delete() throws Exception{
        service.delete(STUDENT_1.getIdStudent());
        verify (repository, times(1)).deleteById(STUDENT_1.getIdStudent());
    }

    @Test
    void readAllOrderByAgeDesc() throws Exception {
        List<Student> response = service.getStudentsOrderByAgeDesc();
        assertNotNull(response);
        assertEquals(response.size(), 2);
        assertEquals(response.get(0).getIdStudent(), 1);
    }
}
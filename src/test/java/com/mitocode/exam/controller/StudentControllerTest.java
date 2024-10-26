package com.mitocode.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.exam.dto.StudentDTO;
import com.mitocode.exam.exception.ModelNotFoundException;
import com.mitocode.exam.model.Student;
import com.mitocode.exam.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService service;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Student STUDENT_1 = new Student(1, "Gregory", "Palacios", "47896514", 32);
    Student STUDENT_2 = new Student(2, "Ernesto", "Medina", "58745869", 24);

    StudentDTO STUDENT_DTO_1 = new StudentDTO(1, "Gregory", "Palacios", "47896514", 32);
    StudentDTO STUDENT_DTO_2 = new StudentDTO(2, "Ernesto", "Medina", "58745869", 24);

    @Test
    void readAllTest() throws Exception {
        List<Student> students = List.of(STUDENT_1, STUDENT_2);

        Mockito.when(service.readAll()).thenReturn(students);
        Mockito.when(modelMapper.map(STUDENT_1, StudentDTO.class)).thenReturn(STUDENT_DTO_1);
        Mockito.when(modelMapper.map(STUDENT_2, StudentDTO.class)).thenReturn(STUDENT_DTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void readByIdTest() throws Exception {
        final int ID = 1;

        Mockito.when(service.readById(any())).thenReturn(STUDENT_1);
        Mockito.when(modelMapper.map(STUDENT_1, StudentDTO.class)).thenReturn(STUDENT_DTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Gregory")));
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(any())).thenReturn(STUDENT_2);
        Mockito.when(modelMapper.map(STUDENT_2, StudentDTO.class)).thenReturn(STUDENT_DTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(STUDENT_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        final int ID = 2;

        Mockito.when(service.update(any(), any())).thenReturn(STUDENT_2);
        Mockito.when(modelMapper.map(STUDENT_2, StudentDTO.class)).thenReturn(STUDENT_DTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/students/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(STUDENT_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ernesto")));
    }

    @Test
    void updateErrorTest() throws Exception {
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND: " + ID)).when(service).update(any(), any());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/students/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(STUDENT_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTest() throws Exception{
        final int ID = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteErrorTest() throws Exception{
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND: " + ID)).when(service).delete(ID);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void readAllStudentsOrderByAgeTest() throws Exception {
        List<Student> students = List.of(STUDENT_1, STUDENT_2);

        Mockito.when(service.getStudentsOrderByAgeDesc()).thenReturn(students);
        Mockito.when(modelMapper.map(STUDENT_1, StudentDTO.class)).thenReturn(STUDENT_DTO_1);
        Mockito.when(modelMapper.map(STUDENT_2, StudentDTO.class)).thenReturn(STUDENT_DTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/order/age")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
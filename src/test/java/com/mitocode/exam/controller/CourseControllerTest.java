package com.mitocode.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.exam.dto.CourseDTO;
import com.mitocode.exam.exception.ModelNotFoundException;
import com.mitocode.exam.model.Course;
import com.mitocode.exam.service.ICourseService;
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
@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseService service;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Course COURSE_1 = new Course(1, "Matemática", "MAT", true);
    Course COURSE_2 = new Course(2, "Lenguaje", "LEN", true);

    CourseDTO COURSE_DTO_1 = new CourseDTO(1, "Matemática", "MAT", true);
    CourseDTO COURSE_DTO_2 = new CourseDTO(2, "Lenguaje", "LEN", true);

    @Test
    void readAllTest() throws Exception {
        List<Course> courses = List.of(COURSE_1, COURSE_2);

        Mockito.when(service.readAll()).thenReturn(courses);
        Mockito.when(modelMapper.map(COURSE_1, CourseDTO.class)).thenReturn(COURSE_DTO_1);
        Mockito.when(modelMapper.map(COURSE_2, CourseDTO.class)).thenReturn(COURSE_DTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void readByIdTest() throws Exception {
        final int ID = 1;

        Mockito.when(service.readById(any())).thenReturn(COURSE_1);
        Mockito.when(modelMapper.map(COURSE_1, CourseDTO.class)).thenReturn(COURSE_DTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Matemática")));
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(any())).thenReturn(COURSE_2);
        Mockito.when(modelMapper.map(COURSE_2, CourseDTO.class)).thenReturn(COURSE_DTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(COURSE_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        final int ID = 2;

        Mockito.when(service.update(any(), any())).thenReturn(COURSE_2);
        Mockito.when(modelMapper.map(COURSE_2, CourseDTO.class)).thenReturn(COURSE_DTO_2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/courses/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(COURSE_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Lenguaje")));
    }

    @Test
    void updateErrorTest() throws Exception {
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND: " + ID)).when(service).update(any(), any());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/courses/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(COURSE_DTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTest() throws Exception{
        final int ID = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteErrorTest() throws Exception{
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND: " + ID)).when(service).delete(ID);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}
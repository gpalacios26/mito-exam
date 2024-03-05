package com.mitocode.exam.controller;

import com.mitocode.exam.dto.StudentDTO;
import com.mitocode.exam.model.Student;
import com.mitocode.exam.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto, @PathVariable("id") Integer id) throws Exception{
        Student obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/age")
    public ResponseEntity<List<StudentDTO>> readAllStudentsOrderByAge() throws Exception{
        List<StudentDTO> list = service.getStudentsOrderByAgeDesc().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    private StudentDTO convertToDto(Student obj){
        return modelMapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return modelMapper.map(dto, Student.class);
    }
}

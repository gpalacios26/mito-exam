package com.mitocode.exam.controller;

import com.mitocode.exam.dto.CourseDTO;
import com.mitocode.exam.model.Course;
import com.mitocode.exam.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
        List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Course obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto, @PathVariable("id") Integer id) throws Exception{
        Course obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CourseDTO convertToDto(Course obj){
        return modelMapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return modelMapper.map(dto, Course.class);
    }
}

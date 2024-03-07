package com.mitocode.exam.controller;

import com.mitocode.exam.dto.EnrollmentDTO;
import com.mitocode.exam.model.Enrollment;
import com.mitocode.exam.service.IEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final IEnrollmentService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception{
        List<EnrollmentDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Enrollment obj = service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> save(@Valid @RequestBody EnrollmentDTO dto) throws Exception{
        Enrollment obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@Valid @RequestBody EnrollmentDTO dto, @PathVariable("id") Integer id) throws Exception{
        Enrollment obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course")
    public ResponseEntity<Map<String, Map<String, Long>>> readStudentsGroupByEnrollmentCourse() throws Exception{
        Map<String, Map<String, Long>> byCourse = service.getStudentsGroupByEnrollmentCourse();
        return ResponseEntity.ok(byCourse);
    }

    @GetMapping("/course2")
    public ResponseEntity<Map<String, List<String>>> readStudentsGroupByEnrollmentCourse2() throws Exception{
        Map<String, List<String>> byCourse = service.getStudentsGroupByEnrollmentCourse2();
        return ResponseEntity.ok(byCourse);
    }

    private EnrollmentDTO convertToDto(Enrollment obj){
        return modelMapper.map(obj, EnrollmentDTO.class);
    }

    private Enrollment convertToEntity(EnrollmentDTO dto){
        return modelMapper.map(dto, Enrollment.class);
    }
}

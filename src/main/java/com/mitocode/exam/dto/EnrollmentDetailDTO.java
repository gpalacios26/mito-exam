package com.mitocode.exam.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDTO {

    private Integer idEnrollmentDetail;

    @JsonBackReference
    private EnrollmentDTO enrollment;

    @NotNull
    private CourseDTO course;

    @NotNull
    private String classroom;
}

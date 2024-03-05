package com.mitocode.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

    private int status;

    private String message;

    private List<T> data;
}

package com.mitocode.exam.service;

import com.mitocode.exam.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student, Integer> {

    List<Student> getStudentsOrderByAgeDesc();
}

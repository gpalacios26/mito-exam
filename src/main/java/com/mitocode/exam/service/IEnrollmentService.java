package com.mitocode.exam.service;

import com.mitocode.exam.model.Enrollment;

import java.util.Map;

public interface IEnrollmentService extends ICRUD<Enrollment, Integer> {

    Map<String, Map<String, Long>> getStudentsGroupByEnrollmentCourse();
}

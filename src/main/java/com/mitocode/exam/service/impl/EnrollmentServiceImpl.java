package com.mitocode.exam.service.impl;

import com.mitocode.exam.model.Enrollment;
import com.mitocode.exam.model.EnrollmentDetail;
import com.mitocode.exam.repository.IEnrollmentRepository;
import com.mitocode.exam.repository.IGenericRepository;
import com.mitocode.exam.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, Integer> implements IEnrollmentService {

    private final IEnrollmentRepository repository;
    @Override
    protected IGenericRepository<Enrollment, Integer> getRepository() {
        return repository;
    }

    @Override
    public Map<String, Map<String, Long>> getStudentsGroupByEnrollmentCourse() {
        Stream<Enrollment> enrollmentStream = repository.findAll().stream();
        Stream<List<EnrollmentDetail>> listDetailsStream = enrollmentStream.map(Enrollment::getDetails);

        Stream<EnrollmentDetail> detailStream = listDetailsStream.flatMap(Collection::stream);
        Map<String, Map<String, Long>> byCourse = detailStream.collect(groupingBy(d -> {
            return d.getCourse().getName();
        }, groupingBy(dd -> dd.getEnrollment().getStudent().getName(), counting())));

        return byCourse.entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, List<String>> getStudentsGroupByEnrollmentCourse2() {
        Stream<Enrollment> enrollmentStream = repository.findAll().stream();
        Stream<List<EnrollmentDetail>> listDetailsStream = enrollmentStream.map(Enrollment::getDetails);

        Stream<EnrollmentDetail> detailStream = listDetailsStream.flatMap(Collection::stream);
        Map<String, Map<String, Long>> byCourse = detailStream.collect(groupingBy(d -> {
            return d.getCourse().getName();
        }, groupingBy(dd -> dd.getEnrollment().getStudent().getName(), counting())));

        Map<String, List<String>> byCourse2 = new HashMap<String, List<String>>();
        byCourse.forEach((key, value) -> {
            List<String> listValues = new ArrayList<String>();
            value.forEach((key2, value2) -> {
                listValues.add(key2);
            });
            byCourse2.put(key, listValues);
        });

        return byCourse2;
    }
}

package com.lee.reservation.system.converter;

import com.lee.reservation.system.service.CourseService;
import com.lee.reservation.system.service.InstructorService;
import com.lee.reservation.system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @Date 2024-10-18 14:35
 */
@Component
@Named("ConverterWorker")
@RequiredArgsConstructor
public class ConverterWorker {
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final CourseService courseService;

    @Named("getStudentName")
    public String getStudentName(Integer id) {
        return studentService.getStudentNameById(id);
    }

    @Named("getInstructorName")
    public String getInstructorName(Integer id) {
        return instructorService.getInstructorNameById(id);
    }

    @Named("getCourseName")
    public String getCourseName(Integer id) {
        return courseService.getCourseName(id);
    }

}
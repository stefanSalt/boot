package com.lee.selection.system.converter;

import com.lee.selection.system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Named("CourseConverterWorker")
@RequiredArgsConstructor
public class CourseConvertWorker {
    private final CourseService courseService;

    @Named("getCourseName")
    public String getCourseName(Integer id) {
        return courseService.getById(id).getName();
    }


}
package com.lee.selection.system.converter;

import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.service.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 *
 * <p>
 * {@code @Date} 2024-10-18 14:35
 */
@Component
@Named("UserConverterWorker")
@RequiredArgsConstructor
public class UserConverterWorker {
    private final UserService userService;


    @Named("getUserName")
    public String getUserName(Integer id) {
        return userService.getUserName(id);
    }


//    @Named("getStudentName")
//    public String getStudentName(Integer id) {
//        return studentService.getStudentNameById(id);
//    }
//
//    @Named("getInstructorName")
//    public String getInstructorName(Integer id) {
//        return instructorService.getInstructorNameById(id);
//    }
//
//    @Named("getCourseName")
//    public String getCourseName(Integer id) {
//        return courseService.getCourseName(id);
//    }

}
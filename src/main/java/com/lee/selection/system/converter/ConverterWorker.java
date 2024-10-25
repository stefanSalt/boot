package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.service.CourseService;
import com.lee.selection.system.service.InstructorService;
import com.lee.selection.system.service.RoleService;
import com.lee.selection.system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 *
 * <p>
 * {@code @Date} 2024-10-18 14:35
 */
@Component
@Named("ConverterWorker")
@RequiredArgsConstructor
public class ConverterWorker {
    private final StudentService studentService;
    private final InstructorService instructorService;
    private final CourseService courseService;

    private final RoleService roleService;

    @Named("getRoleCode")
    public String getRoleCode(Integer id) {
        Role role = roleService.getById(id);
        if (role == null){
            throw new RuntimeException("角色不存在");
        }
        return role.getCode();
    }
    @Named("getClazzName")
    public String getClazzName(Integer id) {
//        return courseService.getCourseName(id);
        return "1班";
    }

    @Named("getMajorName")
    public String getMajorName(Integer id) {
        return "软件工程";
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
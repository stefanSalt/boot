package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.service.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * <p>
 * {@code @Date} 2024-10-18 14:35
 */
@Component
@Named("ConverterWorker")
@RequiredArgsConstructor
public class ConverterWorker {



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
        //return courseService.getCourseName(id);
        return "1班";
    }

    @Named("getMajorName")
    public String getMajorName(Integer id) {
        return "软件工程";
    }

//    @Named("getTeachers")
//    public List<Integer> getTeachers(Integer id) {
//        return courseService.getTeacherIds(id);
//    }

//    @Named("getUserName")
//    public String getUserName(Integer id) {
//        return userService.getUserName(id);
//    }


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
package com.lee.reservation.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.StudentBO;
import com.lee.reservation.system.model.entity.Student;
import com.lee.reservation.system.model.form.StudentForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.vo.StudentPageVO;
import com.lee.reservation.system.model.vo.StudentVO;
import com.lee.reservation.system.model.vo.ProfileVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 学员转换器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Mapper(componentModel = "spring")
public interface StudentConverter {

    StudentPageVO toPageVo(StudentBO bo);

    Page<StudentPageVO> toPageVo(Page<StudentBO> bo);

    StudentForm toForm(Student entity);

    @InheritInverseConfiguration(name = "toForm")
    Student toEntity(StudentForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    StudentVO toVo(Student entity);

    ProfileVO toProfileVo(Student entity);

    Student toEntity(ProfileForm entity);
}
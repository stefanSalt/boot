package com.lee.reservation.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.CourseBO;
import com.lee.reservation.system.model.entity.Course;
import com.lee.reservation.system.model.form.CourseForm;
import com.lee.reservation.system.model.option.CourseOption;
import com.lee.reservation.system.model.vo.CoursePageVO;
import com.lee.reservation.system.model.vo.CourseVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 课程信息转换器
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Mapper(componentModel = "spring")
public interface CourseConverter{

    CoursePageVO toPageVo(CourseBO bo);

    Page<CoursePageVO> toPageVo(Page<CourseBO> bo);

    CourseForm toForm(Course entity);

    @InheritInverseConfiguration(name = "toForm")
    Course toEntity(CourseForm entity);

    CourseVO toVo(Course entity);

    CourseOption toOption(Course entity);
}
package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.CourseBO;
import com.lee.selection.system.model.entity.Course;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.option.CourseOption;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.model.vo.CourseVO;
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
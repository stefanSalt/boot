package com.lee.selection.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.CourseDTO;
import com.lee.selection.system.model.entity.Course;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.bo.CourseBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 课程信息表转换器
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class})
public interface CourseConverter{

    @Mappings(
            {
                    @Mapping(target = "majorName", source = "majorId", qualifiedByName = "getMajorName"),
                    @Mapping(target = "majorId", source = "majorId")
            }
    )
    CoursePageVO toPageVo(CourseBO bo);

    Page<CoursePageVO> toPageVo(Page<CourseBO> bo);

    CourseForm toForm(Course entity);

    @InheritInverseConfiguration(name = "toForm")
    Course toEntity(CourseForm entity);
}
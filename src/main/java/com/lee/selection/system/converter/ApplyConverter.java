package com.lee.selection.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.ApplyDTO;
import com.lee.selection.system.model.entity.Apply;
import com.lee.selection.system.model.vo.ApplyPageVO;
import com.lee.selection.system.model.form.ApplyForm;
import com.lee.selection.system.model.bo.ApplyBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 申请表转换器
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class, UserConverterWorker.class, CourseConvertWorker.class})
public interface ApplyConverter{

    @Mappings(
            {
                    @Mapping(target = "studentName", source = "studentId", qualifiedByName = "getUserName"),
                    @Mapping(target = "teacherName", source = "teacherId", qualifiedByName = "getUserName"),
                    @Mapping(target = "courseName", source = "courseId", qualifiedByName = "getCourseName"),

            }
    )

    ApplyPageVO toPageVo(ApplyBO bo);

    Page<ApplyPageVO> toPageVo(Page<ApplyBO> bo);

    ApplyForm toForm(Apply entity);

    @InheritInverseConfiguration(name = "toForm")
    Apply toEntity(ApplyForm entity);
}
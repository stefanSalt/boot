package com.lee.selection.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.GradeDTO;
import com.lee.selection.system.model.entity.Grade;
import com.lee.selection.system.model.vo.GradePageVO;
import com.lee.selection.system.model.form.GradeForm;
import com.lee.selection.system.model.bo.GradeBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 成绩转换器
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class, UserConverterWorker.class, CourseConvertWorker.class})
public interface GradeConverter{

    @Mappings(
            {
                    @Mapping(target = "studentName", source = "studentId", qualifiedByName = "getUserName"),
                    @Mapping(target = "teacherName", source = "teacherId", qualifiedByName = "getUserName"),
                    @Mapping(target = "courseName", source = "courseId", qualifiedByName = "getCourseName"),

            }
    )
    GradePageVO toPageVo(GradeBO bo);

    Page<GradePageVO> toPageVo(Page<GradeBO> bo);

    GradeForm toForm(Grade entity);

    @InheritInverseConfiguration(name = "toForm")
    Grade toEntity(GradeForm entity);
}
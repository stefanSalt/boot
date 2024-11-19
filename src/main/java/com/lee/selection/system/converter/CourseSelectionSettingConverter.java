package com.lee.selection.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.CourseSelectionSettingDTO;
import com.lee.selection.system.model.entity.CourseSelectionSetting;
import com.lee.selection.system.model.vo.CourseSelectionSettingPageVO;
import com.lee.selection.system.model.form.CourseSelectionSettingForm;
import com.lee.selection.system.model.bo.CourseSelectionSettingBO;

/**
 * 选课设置转换器
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Mapper(componentModel = "spring")
public interface CourseSelectionSettingConverter{

    CourseSelectionSettingPageVO toPageVo(CourseSelectionSettingBO bo);

    Page<CourseSelectionSettingPageVO> toPageVo(Page<CourseSelectionSettingBO> bo);

    CourseSelectionSettingForm toForm(CourseSelectionSetting entity);

    @InheritInverseConfiguration(name = "toForm")
    CourseSelectionSetting toEntity(CourseSelectionSettingForm entity);
}
package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.CourseSelectionSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.CourseSelectionSettingBO;
import com.lee.selection.system.model.query.CourseSelectionSettingPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 选课设置 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-04
 */

@Mapper
public interface CourseSelectionSettingMapper extends BaseMapper<CourseSelectionSetting> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<CourseSelectionSettingBO> listPagedCourseSelectionSettings(Page<CourseSelectionSettingBO> page, CourseSelectionSettingPageQuery queryParams);

}

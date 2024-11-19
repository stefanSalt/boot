package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.CourseSelectionSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.CourseSelectionSettingForm;
import com.lee.selection.system.model.query.CourseSelectionSettingPageQuery;
import com.lee.selection.system.model.vo.CourseSelectionSettingPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 选课设置 服务类
 *
 * @author baomidou
 * @since 2024-11-04
 */
public interface CourseSelectionSettingService extends IService<CourseSelectionSetting> {


    /**
     *选课设置分页列表
     *
     * @return
     */
    IPage<CourseSelectionSettingPageVO> listPagedCourseSelectionSettings(CourseSelectionSettingPageQuery queryParams);


    /**
     * 获取选课设置表单数据
     *
     * @param id 选课设置ID
     * @return
     */
     CourseSelectionSettingForm getCourseSelectionSettingFormData(Long id);


    /**
     * 新增选课设置
     *
     * @param formData 选课设置表单对象
     * @return
     */
    boolean saveCourseSelectionSetting(CourseSelectionSettingForm formData);

    /**
     * 修改选课设置
     *
     * @param id   选课设置ID
     * @param formData 选课设置表单对象
     * @return
     */
    boolean updateCourseSelectionSetting(Long id, CourseSelectionSettingForm formData);


    /**
     * 删除选课设置
     *
     * @param ids 选课设置ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCourseSelectionSettings(String ids);

}

package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.CourseSelectionSetting;
import com.lee.selection.system.mapper.CourseSelectionSettingMapper;
import com.lee.selection.system.service.CourseSelectionSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.CourseSelectionSettingForm;
import com.lee.selection.system.model.query.CourseSelectionSettingPageQuery;
import com.lee.selection.system.model.bo.CourseSelectionSettingBO;
import com.lee.selection.system.model.vo.CourseSelectionSettingPageVO;
import com.lee.selection.system.converter.CourseSelectionSettingConverter;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 选课设置服务实现类
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Service
@RequiredArgsConstructor
public class CourseSelectionSettingServiceImpl extends ServiceImpl<CourseSelectionSettingMapper, CourseSelectionSetting> implements CourseSelectionSettingService {

    private final CourseSelectionSettingConverter courseSelectionSettingConverter;

    /**
    * 获取选课设置分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<CourseSelectionSettingPageVO>} 选课设置分页列表
    */
    @Override
    public IPage<CourseSelectionSettingPageVO> listPagedCourseSelectionSettings(CourseSelectionSettingPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<CourseSelectionSettingBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<CourseSelectionSettingBO> boPage = this.baseMapper.listPagedCourseSelectionSettings(page, queryParams);
    
        // 实体转换
        return courseSelectionSettingConverter.toPageVo(boPage);
    }
    
    /**
     * 获取选课设置表单数据
     *
     * @param id 选课设置ID
     * @return
     */
    @Override
    public CourseSelectionSettingForm getCourseSelectionSettingFormData(Long id) {
        CourseSelectionSetting entity = this.getById(id);
        return courseSelectionSettingConverter.toForm(entity);
    }
    
    /**
     * 新增选课设置
     *
     * @param formData 选课设置表单对象
     * @return
     */
    @Override
    public boolean saveCourseSelectionSetting(CourseSelectionSettingForm formData) {
        // 实体转换 form->entity
        CourseSelectionSetting entity = courseSelectionSettingConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新选课设置
     *
     * @param id   选课设置ID
     * @param formData 选课设置表单对象
     * @return
     */
    @Override
    public boolean updateCourseSelectionSetting(Long id,CourseSelectionSettingForm formData) {
        CourseSelectionSetting entity = courseSelectionSettingConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除选课设置
     *
     * @param ids 选课设置ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCourseSelectionSettings(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的选课设置数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

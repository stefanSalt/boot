package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Course;
import com.lee.selection.system.mapper.CourseMapper;
import com.lee.selection.system.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.query.CoursePageQuery;
import com.lee.selection.system.model.bo.CourseBO;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.converter.CourseConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 课程信息表服务实现类
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseConverter courseConverter;

    /**
    * 获取课程信息表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<CoursePageVO>} 课程信息表分页列表
    */
    @Override
    public IPage<CoursePageVO> listPagedCourses(CoursePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<CourseBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<CourseBO> boPage = this.baseMapper.listPagedCourses(page, queryParams);
    
        // 实体转换
        return courseConverter.toPageVo(boPage);
    }
    
    /**
     * 获取课程信息表表单数据
     *
     * @param id 课程信息表ID
     * @return
     */
    @Override
    public CourseForm getCourseFormData(Long id) {
        Course entity = this.getById(id);
        CourseForm form = courseConverter.toForm(entity);
        form.setTeacherIds(getTeacherIds(entity.getId()));
        return form;
    }
    
    /**
     * 新增课程信息表
     *
     * @param formData 课程信息表表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveCourse(CourseForm formData) {
        // 实体转换 form->entity
        List<Integer> teacherIds = formData.getTeacherIds();
        Course entity = courseConverter.toEntity(formData);
        boolean save = this.save(entity);
        baseMapper.addCourseTeachers(entity.getId(), teacherIds);
        return save;
    }
    
    /**
     * 更新课程信息表
     *
     * @param id   课程信息表ID
     * @param formData 课程信息表表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateCourse(Long id,CourseForm formData) {
        Course entity = courseConverter.toEntity(formData);
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(id);
        baseMapper.deleteCourseTeachers(longs);
        List<Integer> teacherIds = formData.getTeacherIds();
        baseMapper.addCourseTeachers(formData.getId(), teacherIds);
        return this.updateById(entity);
    }


    @Override
    public List<Integer> getTeacherIds(Integer courseId) {
        return baseMapper.getTeacherIds(courseId);
    }
    
    /**
     * 删除课程信息表
     *
     * @param ids 课程信息表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    @Transactional
    public boolean deleteCourses(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的课程信息表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        boolean b = this.removeByIds(idList);
        baseMapper.deleteCourseTeachers(idList);
        return b;
    }
    

}

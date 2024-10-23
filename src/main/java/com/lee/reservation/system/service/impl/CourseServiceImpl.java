package com.lee.reservation.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.reservation.common.exception.BusinessException;
import com.lee.reservation.common.util.DateUtils;
import com.lee.reservation.system.converter.CourseConverter;
import com.lee.reservation.system.mapper.CourseMapper;
import com.lee.reservation.system.model.bo.CourseBO;
import com.lee.reservation.system.model.entity.Course;
import com.lee.reservation.system.model.form.CourseForm;
import com.lee.reservation.system.model.option.CourseOption;
import com.lee.reservation.system.model.query.CoursePageQuery;
import com.lee.reservation.system.model.vo.CoursePageVO;
import com.lee.reservation.system.model.vo.CourseVO;
import com.lee.reservation.system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 课程信息服务实现类
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseConverter courseConverter;

    /**
    * 获取课程信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<CoursePageVO>} 课程信息分页列表
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

    @Override
    public List<CourseOption> listCourses(CoursePageQuery queryParams) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name");
        List<Course> list = this.list(queryWrapper);
        return list.stream().map(courseConverter::toOption).toList();
    }

    /**
     * 获取课程信息表单数据
     *
     * @param id 课程信息ID
     * @return
     */
    @Override
    public CourseForm getCourseFormData(Long id) {
        Course entity = this.getById(id);
        return courseConverter.toForm(entity);
    }
    
    /**
     * 新增课程信息
     *
     * @param formData 课程信息表单对象
     * @return
     */
    @Override
    public boolean saveCourse(CourseForm formData) {
        // 实体转换 form->entity
        Course entity = courseConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新课程信息
     *
     * @param id   课程信息ID
     * @param formData 课程信息表单对象
     * @return
     */
    @Override
    public boolean updateCourse(Long id,CourseForm formData) {
        Course entity = courseConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除课程信息
     *
     * @param ids 课程信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCourses(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的课程信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public CourseVO getCourseDetail(Long id) {
        Course course = this.getById(id);
        if (course == null) {
            throw new BusinessException("课程不存在");
        }
        return courseConverter.toVo(course);
    }

    @Override
    public boolean publishCourse(Long id) {
        Course course = this.getById(id);
        if (course != null) {
            course.setStatus(1);
            return this.updateById(course);
        }
        return false;
    }

    @Override
    public boolean revokeCourse(Long id) {
        Course course = this.getById(id);
        if (course != null) {
            course.setStatus(0);
            return this.updateById(course);
        }
        return false;
    }

    @Override
    public String getCourseName(Integer id) {
        return getById(id).getName();
    }
}

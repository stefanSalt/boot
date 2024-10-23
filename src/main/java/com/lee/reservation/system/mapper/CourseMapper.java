package com.lee.reservation.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.CourseBO;
import com.lee.reservation.system.model.entity.Course;
import com.lee.reservation.system.model.query.CoursePageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 课程信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-17
 */

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<CourseBO> listPagedCourses(Page<CourseBO> page, @Param(value = "queryParams") CoursePageQuery queryParams);

}

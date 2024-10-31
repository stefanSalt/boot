package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.CourseBO;
import com.lee.selection.system.model.query.CoursePageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-31
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
    Page<CourseBO> listPagedCourses(Page<CourseBO> page, CoursePageQuery queryParams);

    void addCourseTeachers(Integer id, List<Integer> teacherIds);


    void deleteCourseTeachers(@Param("idList") List<Long> idList);

    List<Integer> getTeacherIds(Integer courseId);
}

package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.query.CoursePageQuery;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 课程信息表 服务类
 *
 * @author baomidou
 * @since 2024-10-31
 */
public interface CourseService extends IService<Course> {


    /**
     *课程信息表分页列表
     *
     * @return
     */
    IPage<CoursePageVO> listPagedCourses(CoursePageQuery queryParams);


    /**
     * 获取课程信息表表单数据
     *
     * @param id 课程信息表ID
     * @return
     */
     CourseForm getCourseFormData(Long id);


    /**
     * 新增课程信息表
     *
     * @param formData 课程信息表表单对象
     * @return
     */
    boolean saveCourse(CourseForm formData);

    /**
     * 修改课程信息表
     *
     * @param id   课程信息表ID
     * @param formData 课程信息表表单对象
     * @return
     */
    boolean updateCourse(Long id, CourseForm formData);


    List<Integer> getTeacherIds(Integer courseId);

    /**
     * 删除课程信息表
     *
     * @param ids 课程信息表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCourses(String ids);

}

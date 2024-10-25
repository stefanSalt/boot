package com.lee.selection.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Course;
import com.lee.selection.system.model.form.CourseForm;
import com.lee.selection.system.model.option.CourseOption;
import com.lee.selection.system.model.query.CoursePageQuery;
import com.lee.selection.system.model.vo.CoursePageVO;
import com.lee.selection.system.model.vo.CourseVO;

import java.util.List;

/**
 * 课程信息 服务类
 *
 * @author baomidou
 * @since 2024-10-17
 */
public interface CourseService extends IService<Course> {


    /**
     *课程信息分页列表
     *
     * @return
     */
    IPage<CoursePageVO> listPagedCourses(CoursePageQuery queryParams);


    /**
     * 获取课程信息表单数据
     *
     * @param id 课程信息ID
     * @return
     */
     CourseForm getCourseFormData(Long id);


    /**
     * 新增课程信息
     *
     * @param formData 课程信息表单对象
     * @return
     */
    boolean saveCourse(CourseForm formData);

    /**
     * 修改课程信息
     *
     * @param id   课程信息ID
     * @param formData 课程信息表单对象
     * @return
     */
    boolean updateCourse(Long id, CourseForm formData);


    /**
     * 删除课程信息
     *
     * @param ids 课程信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCourses(String ids);

    CourseVO getCourseDetail(Long id);

    boolean publishCourse(Long id);

    boolean revokeCourse(Long id);

    String getCourseName(Integer id);

    List<CourseOption> listCourses(CoursePageQuery queryParams);
}

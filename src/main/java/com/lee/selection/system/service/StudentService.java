package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 学生信息 服务类
 *
 * @author baomidou
 * @since 2024-11-08
 */
public interface StudentService extends IService<Student> {


    /**
     *学生信息分页列表
     *
     * @return
     */
    IPage<Student> listPagedStudents(Student queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取学生信息表单数据
     *
     * @param id 学生信息ID
     * @return
     */
     Student getStudentData(Long id);


    /**
     * 新增学生信息
     *
     * @param formData 学生信息表单对象
     * @return
     */
    boolean saveStudent(Student formData);

    /**
     * 修改学生信息
     *
     * @param id   学生信息ID
     * @param formData 学生信息表单对象
     * @return
     */
    boolean updateStudent(Long id, Student formData);


    /**
     * 删除学生信息
     *
     * @param ids 学生信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteStudents(String ids);

}

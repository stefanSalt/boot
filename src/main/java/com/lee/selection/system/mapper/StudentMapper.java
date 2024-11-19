package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-08
 */

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Student> listPagedStudents(Page<Student> page, Student queryParams);

}

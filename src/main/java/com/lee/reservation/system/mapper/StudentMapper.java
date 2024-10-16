package com.lee.reservation.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.StudentBO;
import com.lee.reservation.system.model.entity.Student;
import com.lee.reservation.system.model.query.StudentPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学员 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-14
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
    Page<StudentBO> listPagedStudents(Page<StudentBO> page,@Param("queryParams") StudentPageQuery queryParams);

}

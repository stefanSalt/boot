package com.lee.reservation.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.InstructorBO;
import com.lee.reservation.system.model.entity.Instructor;
import com.lee.reservation.system.model.query.InstructorPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 教练 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-14
 */

@Mapper
public interface InstructorMapper extends BaseMapper<Instructor> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<InstructorBO> listPagedInstructors(Page<InstructorBO> page,@Param("queryParams") InstructorPageQuery queryParams);

}

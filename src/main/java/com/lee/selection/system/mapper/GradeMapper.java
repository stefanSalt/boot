package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.GradeBO;
import com.lee.selection.system.model.query.GradePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-01
 */

@Mapper
public interface GradeMapper extends BaseMapper<Grade> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<GradeBO> listPagedGrades(Page<GradeBO> page, GradePageQuery queryParams);

}

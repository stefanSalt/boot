package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.MajorBO;
import com.lee.selection.system.model.query.MajorPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 专业信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-25
 */

@Mapper
public interface MajorMapper extends BaseMapper<Major> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<MajorBO> listPagedMajors(Page<MajorBO> page, MajorPageQuery queryParams);

    List<MajorBO> listMajors(MajorPageQuery queryParams);
}

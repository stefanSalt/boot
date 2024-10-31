package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.ClazzBO;
import com.lee.selection.system.model.query.ClazzPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班级 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-30
 */

@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<ClazzBO> listPagedClazzs(Page<ClazzBO> page, ClazzPageQuery queryParams);

    List<ClazzBO> listClazzes(ClazzPageQuery queryParams);
}

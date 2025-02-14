package com.lee.system.mapper;

import com.lee.system.model.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-02-10
 */

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Activity> listPagedActivitys(Page<Activity> page, Activity queryParams);

}

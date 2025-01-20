package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.CheckIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.CheckIn;
import org.apache.ibatis.annotations.Mapper;

/**
 * 讲座签到表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-08
 */

@Mapper
public interface CheckInMapper extends BaseMapper<CheckIn> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<CheckIn> listPagedCheckIns(Page<CheckIn> page, CheckIn queryParams);

}

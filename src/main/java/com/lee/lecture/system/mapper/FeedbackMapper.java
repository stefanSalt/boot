package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 讲座评价表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-08
 */

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Feedback> listPagedFeedbacks(Page<Feedback> page, Feedback queryParams);

}

package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.Review;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-05
 */

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Review> listPagedReviews(Page<Review> page, Review queryParams);

    List<Review> listReviews(@Param("queryParams") Review queryParams);
}

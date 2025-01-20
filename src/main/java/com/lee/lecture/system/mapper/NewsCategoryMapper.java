package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.NewsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.NewsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资讯分类表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-07
 */

@Mapper
public interface NewsCategoryMapper extends BaseMapper<NewsCategory> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<NewsCategory> listPagedNewsCategorys(Page<NewsCategory> page, NewsCategory queryParams);

    List<NewsCategory> listNewsCategoryOptions();
}

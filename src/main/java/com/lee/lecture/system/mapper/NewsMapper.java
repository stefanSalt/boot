package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-07
 */

@Mapper
public interface NewsMapper extends BaseMapper<News> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<News> listPagedNewss(Page<News> page, News queryParams);

}

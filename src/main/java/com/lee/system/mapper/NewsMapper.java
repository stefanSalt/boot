package com.lee.system.mapper;

import com.lee.system.model.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-02-10
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

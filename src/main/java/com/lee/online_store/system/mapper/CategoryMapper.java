package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-26
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取用户列表
     *
     * @param queryParams 查询参数
     * @return
     */
    List<Category> listCategorys( @Param("queryParams") Category queryParams);

}

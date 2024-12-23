package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

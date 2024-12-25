package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-25
 */

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Product> listPagedProducts(Page<Product> page, Product queryParams);

}

package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.dto.ProductQuery;
import com.lee.online_store.system.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-27
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
    Page<Product> listPagedProducts(Page<Product> page, ProductQuery queryParams);

    List<Product> listProducts(@Param("queryParams") ProductQuery queryParams);

    List<Product> listDiscountByIds(List<Long> idList);
}

package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Promotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 促销活动 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-27
 */

@Mapper
public interface PromotionMapper extends BaseMapper<Promotion> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Promotion> listPagedPromotions(Page<Promotion> page, Promotion queryParams);

    Integer removeProductsFromPromotion(Long promotionId);

    Integer addProductsToPromotion(Long promotionId, List<Integer> productIds);

    List<Integer> getProductIdsOfPromotion(Long id);
}

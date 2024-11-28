package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.dto.OrderQuery;
import com.lee.online_store.system.model.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-28
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Order> listPagedOrders(Page<Order> page, OrderQuery queryParams);

}

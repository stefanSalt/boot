package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单详情信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-25
 */

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<OrderDetail> listPagedOrderDetails(Page<OrderDetail> page, OrderDetail queryParams);

    void insertBatch(List<OrderDetail> orderItems);

    void deleteByOrderId(Long id);
}

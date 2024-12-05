package com.lee.online_store.system.service;

import com.lee.online_store.system.model.dto.OrderQuery;
import com.lee.online_store.system.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 订单 服务类
 *
 * @author baomidou
 * @since 2024-11-28
 */
public interface OrderService extends IService<Order> {


    /**
     *订单分页列表
     *
     * @return
     */
    IPage<Order> listPagedOrders(OrderQuery queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return
     */
     Order getOrderData(Long id);


    /**
     * 新增订单
     *
     * @param formData 订单表单对象
     * @return
     */
    boolean saveOrder(Order formData);

    /**
     * 修改订单
     *
     * @param id   订单ID
     * @param formData 订单表单对象
     * @return
     */
    boolean updateOrder(Long id, Order formData);


    /**
     * 删除订单
     *
     * @param ids 订单ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteOrders(String ids);

    List<Order> listOrders(OrderQuery queryParams);
}

package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 订单信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface OrderService extends IService<Order> {


    /**
     *订单信息分页列表
     *
     * @return
     */
    IPage<Order> listPagedOrders(Order queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取订单信息表单数据
     *
     * @param id 订单信息ID
     * @return
     */
     Order getOrderData(Long id);


    /**
     * 新增订单信息
     *
     * @param formData 订单信息表单对象
     * @return
     */
    boolean saveOrder(Order formData);

    /**
     * 修改订单信息
     *
     * @param id   订单信息ID
     * @param formData 订单信息表单对象
     * @return
     */
    boolean updateOrder(Long id, Order formData);


    /**
     * 删除订单信息
     *
     * @param ids 订单信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteOrders(String ids);

    List<Order> listOrders(Order queryParams);
}

package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.OrderDetail;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 订单详情信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface OrderDetailService extends IService<OrderDetail> {


    /**
     *订单详情信息分页列表
     *
     * @return
     */
    IPage<OrderDetail> listPagedOrderDetails(OrderDetail queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取订单详情信息表单数据
     *
     * @param id 订单详情信息ID
     * @return
     */
     OrderDetail getOrderDetailData(Long id);


    /**
     * 新增订单详情信息
     *
     * @param formData 订单详情信息表单对象
     * @return
     */
    boolean saveOrderDetail(OrderDetail formData);

    /**
     * 修改订单详情信息
     *
     * @param id   订单详情信息ID
     * @param formData 订单详情信息表单对象
     * @return
     */
    boolean updateOrderDetail(Long id, OrderDetail formData);


    /**
     * 删除订单详情信息
     *
     * @param ids 订单详情信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteOrderDetails(String ids);

}

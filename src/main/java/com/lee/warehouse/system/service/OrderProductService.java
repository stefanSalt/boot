package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.OrderProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 订单商品关联 服务类
 *
 * @author baomidou
 * @since 2024-11-28
 */
public interface OrderProductService extends IService<OrderProduct> {


    /**
     *订单商品关联分页列表
     *
     * @return
     */
    IPage<OrderProduct> listPagedOrderProducts(OrderProduct queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取订单商品关联表单数据
     *
     * @param id 订单商品关联ID
     * @return
     */
     OrderProduct getOrderProductData(Long id);


    /**
     * 新增订单商品关联
     *
     * @param formData 订单商品关联表单对象
     * @return
     */
    boolean saveOrderProduct(OrderProduct formData);

    /**
     * 修改订单商品关联
     *
     * @param id   订单商品关联ID
     * @param formData 订单商品关联表单对象
     * @return
     */
    boolean updateOrderProduct(Long id, OrderProduct formData);


    /**
     * 删除订单商品关联
     *
     * @param ids 订单商品关联ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteOrderProducts(String ids);

}

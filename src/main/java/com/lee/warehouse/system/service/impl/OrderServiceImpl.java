package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.mapper.OrderDetailMapper;
import com.lee.warehouse.system.model.entity.Order;
import com.lee.warehouse.system.mapper.OrderMapper;
import com.lee.warehouse.system.model.entity.OrderDetail;
import com.lee.warehouse.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
/**
 * 订单信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    /**
    * 获取订单信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Order>} 订单信息分页列表
    */
    @Override
    public IPage<Order> listPagedOrders(Order queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Order> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Order> boPage = this.baseMapper.listPagedOrders(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取订单信息表单数据
     *
     * @param id 订单信息ID
     * @return
     */
    @Override
    public Order getOrderData(Long id) {
        Order entity = this.baseMapper.getOrderData(id);
        return entity;
    }
    
    /**
     * 新增订单信息
     *
     * @param formData 订单信息表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveOrder(Order formData) {

        boolean save = this.save(formData);
        List<OrderDetail> orderItems = formData.getOrderItems();
        orderItems.forEach(item -> item.setOrderId(formData.getId()));
        orderDetailMapper.insertBatch(orderItems);
        return save;
    }
    
    /**
     * 更新订单信息
     *
     * @param id   订单信息ID
     * @param formData 订单信息表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateOrder(Long id,Order formData) {
        orderDetailMapper.deleteByOrderId(id);
        orderDetailMapper.insertBatch(formData.getOrderItems());
        return this.updateById(formData);
    }
    
    /**
     * 删除订单信息
     *
     * @param ids 订单信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrders(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    /**
     * 获取订单信息下拉列表
     *
     * @param queryParams 查询参数
     * @return
     */
    @Override
    public List<Order> listOrders(Order queryParams) {
        List<Order> list = this.baseMapper.listOrders(queryParams);
        return list;
    }
    

}

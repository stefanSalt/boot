package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.mapper.OrderProductMapper;
import com.lee.online_store.system.model.dto.OrderQuery;
import com.lee.online_store.system.model.entity.Order;
import com.lee.online_store.system.mapper.OrderMapper;
import com.lee.online_store.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
/**
 * 订单服务实现类
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;


    /**
    * 获取订单分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Order>} 订单分页列表
    */
    @Override
    public IPage<Order> listPagedOrders(OrderQuery queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Order> page = new Page<>(pageNum, pageSize);

    
        // 查询数据
        Page<Order> boPage = this.baseMapper.listPagedOrders(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return
     */
    @Override
    public Order getOrderData(Long id) {
        Order entity = this.getById(id);
        entity.setOrderProducts(orderProductMapper.listOrderProductsByOrderId(entity.getId()));
        return entity;
    }
    
    /**
     * 新增订单
     *
     * @param formData 订单表单对象
     * @return
     */
    @Override
    public boolean saveOrder(Order formData) {

        return this.save(formData);
    }
    
    /**
     * 更新订单
     *
     * @param id   订单ID
     * @param formData 订单表单对象
     * @return
     */
    @Override
    public boolean updateOrder(Long id,Order formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除订单
     *
     * @param ids 订单ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrders(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.OrderDetail;
import com.lee.warehouse.system.mapper.OrderDetailMapper;
import com.lee.warehouse.system.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.OrderDetail;

import java.util.Arrays;
import java.util.List;
/**
 * 订单详情信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


    /**
    * 获取订单详情信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<OrderDetail>} 订单详情信息分页列表
    */
    @Override
    public IPage<OrderDetail> listPagedOrderDetails(OrderDetail queryParams, Integer pageNum, Integer pageSize) {
    

        Page<OrderDetail> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<OrderDetail> boPage = this.baseMapper.listPagedOrderDetails(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取订单详情信息表单数据
     *
     * @param id 订单详情信息ID
     * @return
     */
    @Override
    public OrderDetail getOrderDetailData(Long id) {
        OrderDetail entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增订单详情信息
     *
     * @param formData 订单详情信息表单对象
     * @return
     */
    @Override
    public boolean saveOrderDetail(OrderDetail formData) {

        return this.save(formData);
    }
    
    /**
     * 更新订单详情信息
     *
     * @param id   订单详情信息ID
     * @param formData 订单详情信息表单对象
     * @return
     */
    @Override
    public boolean updateOrderDetail(Long id,OrderDetail formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除订单详情信息
     *
     * @param ids 订单详情信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrderDetails(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单详情信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

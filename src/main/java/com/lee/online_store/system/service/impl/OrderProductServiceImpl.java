package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.OrderProduct;
import com.lee.online_store.system.mapper.OrderProductMapper;
import com.lee.online_store.system.service.OrderProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.online_store.system.model.entity.OrderProduct;

import java.util.Arrays;
import java.util.List;
/**
 * 订单商品关联服务实现类
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {


    /**
    * 获取订单商品关联分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<OrderProduct>} 订单商品关联分页列表
    */
    @Override
    public IPage<OrderProduct> listPagedOrderProducts(OrderProduct queryParams, Integer pageNum, Integer pageSize) {
    

        Page<OrderProduct> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
//        Page<OrderProduct> boPage = this.baseMapper.listPagedOrderProducts(page, queryParams);
    
        // 实体转换
        return null;
    }
    
    /**
     * 获取订单商品关联表单数据
     *
     * @param id 订单商品关联ID
     * @return
     */
    @Override
    public OrderProduct getOrderProductData(Long id) {
        OrderProduct entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增订单商品关联
     *
     * @param formData 订单商品关联表单对象
     * @return
     */
    @Override
    public boolean saveOrderProduct(OrderProduct formData) {

        return this.save(formData);
    }
    
    /**
     * 更新订单商品关联
     *
     * @param id   订单商品关联ID
     * @param formData 订单商品关联表单对象
     * @return
     */
    @Override
    public boolean updateOrderProduct(Long id,OrderProduct formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除订单商品关联
     *
     * @param ids 订单商品关联ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrderProducts(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单商品关联数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

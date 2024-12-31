package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.mapper.OrderMapper;
import com.lee.warehouse.system.model.entity.InventoryTransaction;
import com.lee.warehouse.system.mapper.InventoryTransactionMapper;
import com.lee.warehouse.system.model.entity.Order;
import com.lee.warehouse.system.service.InventoryTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.InventoryTransaction;

import java.util.Arrays;
import java.util.List;
/**
 * 库存交易信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-26
 */
@Service
@RequiredArgsConstructor
public class InventoryTransactionServiceImpl extends ServiceImpl<InventoryTransactionMapper, InventoryTransaction> implements InventoryTransactionService {

    private final InventoryTransactionMapper inventoryTransactionMapper;
    private final OrderMapper orderMapper;

    /**
    * 获取库存交易信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<InventoryTransaction>} 库存交易信息分页列表
    */
    @Override
    public IPage<InventoryTransaction> listPagedInventoryTransactions(InventoryTransaction queryParams, Integer pageNum, Integer pageSize) {
    

        Page<InventoryTransaction> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<InventoryTransaction> boPage = this.baseMapper.listPagedInventoryTransactions(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取库存交易信息表单数据
     *
     * @param id 库存交易信息ID
     * @return
     */
    @Override
    public InventoryTransaction getInventoryTransactionData(Long id) {
        InventoryTransaction entity = this.getById(id);
        Order orderData = orderMapper.getOrderData(Long.valueOf(entity.getOrderId()));
        entity.setOrder(orderData);
        return entity;
    }
    
    /**
     * 新增库存交易信息
     *
     * @param formData 库存交易信息表单对象
     * @return
     */
    @Override
    public boolean saveInventoryTransaction(InventoryTransaction formData) {

        return this.save(formData);
    }
    
    /**
     * 更新库存交易信息
     *
     * @param id   库存交易信息ID
     * @param formData 库存交易信息表单对象
     * @return
     */
    @Override
    public boolean updateInventoryTransaction(Long id,InventoryTransaction formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除库存交易信息
     *
     * @param ids 库存交易信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteInventoryTransactions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的库存交易信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

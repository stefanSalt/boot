package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.InventoryTransaction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.InventoryTransaction;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 库存交易信息 服务类
 *
 * @author baomidou
 * @since 2024-12-26
 */
public interface InventoryTransactionService extends IService<InventoryTransaction> {


    /**
     *库存交易信息分页列表
     *
     * @return
     */
    IPage<InventoryTransaction> listPagedInventoryTransactions(InventoryTransaction queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取库存交易信息表单数据
     *
     * @param id 库存交易信息ID
     * @return
     */
     InventoryTransaction getInventoryTransactionData(Long id);


    /**
     * 新增库存交易信息
     *
     * @param formData 库存交易信息表单对象
     * @return
     */
    boolean saveInventoryTransaction(InventoryTransaction formData);

    /**
     * 修改库存交易信息
     *
     * @param id   库存交易信息ID
     * @param formData 库存交易信息表单对象
     * @return
     */
    boolean updateInventoryTransaction(Long id, InventoryTransaction formData);


    /**
     * 删除库存交易信息
     *
     * @param ids 库存交易信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteInventoryTransactions(String ids);

}

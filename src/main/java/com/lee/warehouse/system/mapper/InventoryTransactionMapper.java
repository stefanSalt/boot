package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.InventoryTransaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.InventoryTransaction;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存交易信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-26
 */

@Mapper
public interface InventoryTransactionMapper extends BaseMapper<InventoryTransaction> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<InventoryTransaction> listPagedInventoryTransactions(Page<InventoryTransaction> page, InventoryTransaction queryParams);

}

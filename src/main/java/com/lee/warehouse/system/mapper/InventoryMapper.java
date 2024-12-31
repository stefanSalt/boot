package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-25
 */

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Inventory> listPagedInventorys(Page<Inventory> page, Inventory queryParams);

}

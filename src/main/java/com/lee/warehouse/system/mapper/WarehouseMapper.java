package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 仓库信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-25
 */

@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Warehouse> listPagedWarehouses(Page<Warehouse> page, Warehouse queryParams);

    /**
     * 获取仓库列表下拉选项
     *
     * @return
     */
    List<Warehouse> listWarehouseOptions();
}

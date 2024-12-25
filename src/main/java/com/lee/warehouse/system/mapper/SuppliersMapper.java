package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Suppliers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 供应商信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-24
 */

@Mapper
public interface SuppliersMapper extends BaseMapper<Suppliers> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Suppliers> listPagedSuppliers(Page<Suppliers> page, Suppliers queryParams);

    List<Suppliers> listSuppliersOptions();
}

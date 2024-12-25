package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.Warehouse;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 仓库信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface WarehouseService extends IService<Warehouse> {


    /**
     *仓库信息分页列表
     *
     * @return
     */
    IPage<Warehouse> listPagedWarehouses(Warehouse queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取仓库信息表单数据
     *
     * @param id 仓库信息ID
     * @return
     */
     Warehouse getWarehouseData(Long id);


    /**
     * 新增仓库信息
     *
     * @param formData 仓库信息表单对象
     * @return
     */
    boolean saveWarehouse(Warehouse formData);

    /**
     * 修改仓库信息
     *
     * @param id   仓库信息ID
     * @param formData 仓库信息表单对象
     * @return
     */
    boolean updateWarehouse(Long id, Warehouse formData);


    /**
     * 删除仓库信息
     *
     * @param ids 仓库信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteWarehouses(String ids);

    /**
     * 获取仓库信息列表option
     *
     * @return
     */
    List<Warehouse> listWarehouseOptions();
}

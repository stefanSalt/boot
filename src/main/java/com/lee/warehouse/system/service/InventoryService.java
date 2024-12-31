package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.Inventory;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 库存信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface InventoryService extends IService<Inventory> {


    /**
     *库存信息分页列表
     *
     * @return
     */
    IPage<Inventory> listPagedInventorys(Inventory queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取库存信息表单数据
     *
     * @param id 库存信息ID
     * @return
     */
     Inventory getInventoryData(Long id);


    /**
     * 新增库存信息
     *
     * @param formData 库存信息表单对象
     * @return
     */
    boolean saveInventory(Inventory formData);

    /**
     * 修改库存信息
     *
     * @param id   库存信息ID
     * @param formData 库存信息表单对象
     * @return
     */
    boolean updateInventory(Long id, Inventory formData);


    /**
     * 删除库存信息
     *
     * @param ids 库存信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteInventorys(String ids);

}

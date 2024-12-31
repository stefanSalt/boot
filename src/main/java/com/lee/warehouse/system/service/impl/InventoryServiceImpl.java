package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Inventory;
import com.lee.warehouse.system.mapper.InventoryMapper;
import com.lee.warehouse.system.service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.Inventory;

import java.util.Arrays;
import java.util.List;
/**
 * 库存信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {


    /**
    * 获取库存信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Inventory>} 库存信息分页列表
    */
    @Override
    public IPage<Inventory> listPagedInventorys(Inventory queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Inventory> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Inventory> boPage = this.baseMapper.listPagedInventorys(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取库存信息表单数据
     *
     * @param id 库存信息ID
     * @return
     */
    @Override
    public Inventory getInventoryData(Long id) {
        Inventory entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增库存信息
     *
     * @param formData 库存信息表单对象
     * @return
     */
    @Override
    public boolean saveInventory(Inventory formData) {

        return this.save(formData);
    }
    
    /**
     * 更新库存信息
     *
     * @param id   库存信息ID
     * @param formData 库存信息表单对象
     * @return
     */
    @Override
    public boolean updateInventory(Long id,Inventory formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除库存信息
     *
     * @param ids 库存信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteInventorys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的库存信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

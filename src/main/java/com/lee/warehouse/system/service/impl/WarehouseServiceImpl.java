package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Warehouse;
import com.lee.warehouse.system.mapper.WarehouseMapper;
import com.lee.warehouse.system.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.Warehouse;

import java.util.Arrays;
import java.util.List;
/**
 * 仓库信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {


    /**
    * 获取仓库信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Warehouse>} 仓库信息分页列表
    */
    @Override
    public IPage<Warehouse> listPagedWarehouses(Warehouse queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Warehouse> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Warehouse> boPage = this.baseMapper.listPagedWarehouses(page, queryParams);

        return boPage;
    }

    /**
     * 获取仓库信息下拉选项
     * @return
     */
    @Override
    public List<Warehouse> listWarehouseOptions() {
        return this.baseMapper.listWarehouseOptions();
    }

    /**
     * 获取仓库信息表单数据
     *
     * @param id 仓库信息ID
     * @return
     */
    @Override
    public Warehouse getWarehouseData(Long id) {
        Warehouse entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增仓库信息
     *
     * @param formData 仓库信息表单对象
     * @return
     */
    @Override
    public boolean saveWarehouse(Warehouse formData) {

        return this.save(formData);
    }
    
    /**
     * 更新仓库信息
     *
     * @param id   仓库信息ID
     * @param formData 仓库信息表单对象
     * @return
     */
    @Override
    public boolean updateWarehouse(Long id,Warehouse formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除仓库信息
     *
     * @param ids 仓库信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteWarehouses(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的仓库信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

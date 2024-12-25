package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Suppliers;
import com.lee.warehouse.system.mapper.SuppliersMapper;
import com.lee.warehouse.system.service.SuppliersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
/**
 * 供应商信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-24
 */
@Service
@RequiredArgsConstructor
public class SuppliersServiceImpl extends ServiceImpl<SuppliersMapper, Suppliers> implements SuppliersService {


    /**
    * 获取供应商信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Suppliers>} 供应商信息分页列表
    */
    @Override
    public IPage<Suppliers> listPagedSupplierss(Suppliers queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Suppliers> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Suppliers> boPage = this.baseMapper.listPagedSuppliers(page, queryParams);
    

        return boPage;
    }

    /**
     * 获取供应商信息下拉选项
     *
     * @return
     */
    @Override
    public List<Suppliers> listSuppliersOptions() {
        List<Suppliers> list = this.baseMapper.listSuppliersOptions();
        return list;
    }
    
    /**
     * 获取供应商信息表单数据
     *
     * @param id 供应商信息ID
     * @return
     */
    @Override
    public Suppliers getSuppliersData(Long id) {
        Suppliers entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增供应商信息
     *
     * @param formData 供应商信息表单对象
     * @return
     */
    @Override
    public boolean saveSuppliers(Suppliers formData) {

        return this.save(formData);
    }
    
    /**
     * 更新供应商信息
     *
     * @param id   供应商信息ID
     * @param formData 供应商信息表单对象
     * @return
     */
    @Override
    public boolean updateSuppliers(Long id,Suppliers formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除供应商信息
     *
     * @param ids 供应商信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteSuppliers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的供应商信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Suppliers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 供应商信息 服务类
 *
 * @author baomidou
 * @since 2024-12-24
 */
public interface SuppliersService extends IService<Suppliers> {


    /**
     *供应商信息分页列表
     *
     * @return
     */
    IPage<Suppliers> listPagedSupplierss(Suppliers queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取供应商信息表单数据
     *
     * @param id 供应商信息ID
     * @return
     */
     Suppliers getSuppliersData(Long id);


    /**
     * 新增供应商信息
     *
     * @param formData 供应商信息表单对象
     * @return
     */
    boolean saveSuppliers(Suppliers formData);

    /**
     * 修改供应商信息
     *
     * @param id   供应商信息ID
     * @param formData 供应商信息表单对象
     * @return
     */
    boolean updateSuppliers(Long id, Suppliers formData);


    /**
     * 删除供应商信息
     *
     * @param ids 供应商信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteSuppliers(String ids);

    List<Suppliers> listSuppliersOptions();
}


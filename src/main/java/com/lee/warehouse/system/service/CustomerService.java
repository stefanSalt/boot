package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.Customer;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 客户信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface CustomerService extends IService<Customer> {


    /**
     *客户信息分页列表
     *
     * @return
     */
    IPage<Customer> listPagedCustomers(Customer queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取客户信息表单数据
     *
     * @param id 客户信息ID
     * @return
     */
     Customer getCustomerData(Long id);


    /**
     * 新增客户信息
     *
     * @param formData 客户信息表单对象
     * @return
     */
    boolean saveCustomer(Customer formData);

    /**
     * 修改客户信息
     *
     * @param id   客户信息ID
     * @param formData 客户信息表单对象
     * @return
     */
    boolean updateCustomer(Long id, Customer formData);


    /**
     * 删除客户信息
     *
     * @param ids 客户信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCustomers(String ids);

    List<Customer> listCustomerOptions();
}

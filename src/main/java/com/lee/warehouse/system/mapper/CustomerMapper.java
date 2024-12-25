package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-12-25
 */

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Customer> listPagedCustomers(Page<Customer> page, Customer queryParams);

    /**
     * 获取客户选项列表
     *
     * @return
     */
    List<Customer> listCustomerOptions();
}

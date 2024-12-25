package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Customer;
import com.lee.warehouse.system.mapper.CustomerMapper;
import com.lee.warehouse.system.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.Customer;

import java.util.Arrays;
import java.util.List;
/**
 * 客户信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {


    /**
    * 获取客户信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Customer>} 客户信息分页列表
    */
    @Override
    public IPage<Customer> listPagedCustomers(Customer queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Customer> page = new Page<>(pageNum, pageSize);

    
        // 查询数据
        Page<Customer> boPage = this.baseMapper.listPagedCustomers(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取客户信息表单数据
     *
     * @param id 客户信息ID
     * @return
     */
    @Override
    public Customer getCustomerData(Long id) {
        Customer entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增客户信息
     *
     * @param formData 客户信息表单对象
     * @return
     */
    @Override
    public boolean saveCustomer(Customer formData) {

        return this.save(formData);
    }
    
    /**
     * 更新客户信息
     *
     * @param id   客户信息ID
     * @param formData 客户信息表单对象
     * @return true|false
     */
    @Override
    public boolean updateCustomer(Long id,Customer formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除客户信息
     *
     * @param ids 客户信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCustomers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的客户信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    /**
     * 获取客户信息下拉选项
     *
     * @return
     */
    @Override
    public List<Customer> listCustomerOptions() {
        return this.baseMapper.listCustomerOptions();
    }


}

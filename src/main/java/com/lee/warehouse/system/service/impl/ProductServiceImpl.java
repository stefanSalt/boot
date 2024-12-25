package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Product;
import com.lee.warehouse.system.mapper.ProductMapper;
import com.lee.warehouse.system.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.warehouse.system.model.entity.Product;

import java.util.Arrays;
import java.util.List;
/**
 * 产品信息服务实现类
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    /**
    * 获取产品信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Product>} 产品信息分页列表
    */
    @Override
    public IPage<Product> listPagedProducts(Product queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Product> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Product> boPage = this.baseMapper.listPagedProducts(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取产品信息表单数据
     *
     * @param id 产品信息ID
     * @return
     */
    @Override
    public Product getProductData(Long id) {
        Product entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增产品信息
     *
     * @param formData 产品信息表单对象
     * @return
     */
    @Override
    public boolean saveProduct(Product formData) {

        return this.save(formData);
    }
    
    /**
     * 更新产品信息
     *
     * @param id   产品信息ID
     * @param formData 产品信息表单对象
     * @return
     */
    @Override
    public boolean updateProduct(Long id,Product formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除产品信息
     *
     * @param ids 产品信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteProducts(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的产品信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

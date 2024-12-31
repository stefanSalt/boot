package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.warehouse.system.model.entity.Product;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 产品信息 服务类
 *
 * @author baomidou
 * @since 2024-12-25
 */
public interface ProductService extends IService<Product> {


    /**
     *产品信息分页列表
     *
     * @return
     */
    IPage<Product> listPagedProducts(Product queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取产品信息表单数据
     *
     * @param id 产品信息ID
     * @return
     */
     Product getProductData(Long id);


    /**
     * 新增产品信息
     *
     * @param formData 产品信息表单对象
     * @return
     */
    boolean saveProduct(Product formData);

    /**
     * 修改产品信息
     *
     * @param id   产品信息ID
     * @param formData 产品信息表单对象
     * @return
     */
    boolean updateProduct(Long id, Product formData);


    /**
     * 删除产品信息
     *
     * @param ids 产品信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteProducts(String ids);

    List<Product> listProductOptions();
}

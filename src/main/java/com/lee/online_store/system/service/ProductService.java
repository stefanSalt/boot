package com.lee.online_store.system.service;

import com.lee.online_store.system.model.dto.ProductQuery;
import com.lee.online_store.system.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 商品信息 服务类
 *
 * @author baomidou
 * @since 2024-11-26
 */
public interface ProductService extends IService<Product> {


    /**
     *商品信息分页列表
     *
     * @return
     */
    IPage<Product> listPagedProducts(ProductQuery queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取商品信息表单数据
     *
     * @param id 商品信息ID
     * @return
     */
     Product getProductData(Long id);


    /**
     * 新增商品信息
     *
     * @param formData 商品信息表单对象
     * @return
     */
    boolean saveProduct(Product formData);

    /**
     * 修改商品信息
     *
     * @param id   商品信息ID
     * @param formData 商品信息表单对象
     * @return
     */
    boolean updateProduct(Long id, Product formData);


    /**
     * 删除商品信息
     *
     * @param ids 商品信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteProducts(String ids);

    List<Product> listProducts(ProductQuery queryParams);

    List<Product> listProductsByIds(String ids);

    List<Product> listDiscountByIds(String ids);
}

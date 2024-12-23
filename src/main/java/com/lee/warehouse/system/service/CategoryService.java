package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品分类 服务类
 *
 * @author baomidou
 * @since 2024-11-26
 */
public interface CategoryService extends IService<Category> {


    /**
     *商品分类分页列表
     *
     * @return
     */
    List<Category> listCategorys(Category queryParams);


    /**
     * 获取商品分类表单数据
     *
     * @param id 商品分类ID
     * @return
     */
     Category getCategoryData(Long id);


    /**
     * 新增商品分类
     *
     * @param formData 商品分类表单对象
     * @return
     */
    boolean saveCategory(Category formData);

    /**
     * 修改商品分类
     *
     * @param id   商品分类ID
     * @param formData 商品分类表单对象
     * @return
     */
    boolean updateCategory(Long id, Category formData);


    /**
     * 删除商品分类
     *
     * @param ids 商品分类ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCategorys(String ids);

}

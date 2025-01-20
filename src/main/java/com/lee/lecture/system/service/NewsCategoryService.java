package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.NewsCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.lecture.system.model.entity.NewsCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 资讯分类表 服务类
 *
 * @author baomidou
 * @since 2025-01-07
 */
public interface NewsCategoryService extends IService<NewsCategory> {


    /**
     *资讯分类表分页列表
     *
     * @return
     */
    IPage<NewsCategory> listPagedNewsCategorys(NewsCategory queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取资讯分类表表单数据
     *
     * @param id 资讯分类表ID
     * @return
     */
     NewsCategory getNewsCategoryData(Long id);


    /**
     * 新增资讯分类表
     *
     * @param formData 资讯分类表表单对象
     * @return
     */
    boolean saveNewsCategory(NewsCategory formData);

    /**
     * 修改资讯分类表
     *
     * @param id   资讯分类表ID
     * @param formData 资讯分类表表单对象
     * @return
     */
    boolean updateNewsCategory(Long id, NewsCategory formData);


    /**
     * 删除资讯分类表
     *
     * @param ids 资讯分类表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNewsCategorys(String ids);

    /**
     * 资讯分类表下拉选项列表
     *
     * @return
     */
    List<NewsCategory> listNewsCategoryOptions();
}

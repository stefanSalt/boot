package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.NewsCategory;
import com.lee.lecture.system.mapper.NewsCategoryMapper;
import com.lee.lecture.system.service.NewsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.lecture.system.model.entity.NewsCategory;

import java.util.Arrays;
import java.util.List;
/**
 * 资讯分类表服务实现类
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements NewsCategoryService {


    /**
    * 获取资讯分类表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<NewsCategory>} 资讯分类表分页列表
    */
    @Override
    public IPage<NewsCategory> listPagedNewsCategorys(NewsCategory queryParams, Integer pageNum, Integer pageSize) {
    

        Page<NewsCategory> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<NewsCategory> boPage = this.baseMapper.listPagedNewsCategorys(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取资讯分类表表单数据
     *
     * @param id 资讯分类表ID
     * @return
     */
    @Override
    public NewsCategory getNewsCategoryData(Long id) {
        NewsCategory entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增资讯分类表
     *
     * @param formData 资讯分类表表单对象
     * @return
     */
    @Override
    public boolean saveNewsCategory(NewsCategory formData) {

        return this.save(formData);
    }
    
    /**
     * 更新资讯分类表
     *
     * @param id   资讯分类表ID
     * @param formData 资讯分类表表单对象
     * @return
     */
    @Override
    public boolean updateNewsCategory(Long id,NewsCategory formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除资讯分类表
     *
     * @param ids 资讯分类表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteNewsCategorys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的资讯分类表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public List<NewsCategory> listNewsCategoryOptions() {
        List<NewsCategory> list = this.baseMapper.listNewsCategoryOptions();
        return list;
    }


}

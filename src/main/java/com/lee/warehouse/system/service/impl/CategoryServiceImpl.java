package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.util.TreeUtils;
import com.lee.warehouse.system.model.entity.Category;
import com.lee.warehouse.system.mapper.CategoryMapper;
import com.lee.warehouse.system.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 商品分类服务实现类
 *
 * @author baomidou
 * @since 2024-11-26
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    /**
    * 获取商品分类分页列表
    *
    * @param queryParams 查询参数
     *
    * @return {@link IPage<Category>} 商品分类分页列表
    */
    @Override
    public List<Category> listCategorys(Category queryParams) {

    
        // 查询数据
        List<Category> listCategorys = this.baseMapper.listCategorys(queryParams);
        if (listCategorys.isEmpty()||queryParams.getCategoryName()!=null){
            return  listCategorys;
        }

        //构建树形结构
        Collection<Category> tree = TreeUtils.toTree(listCategorys, "id", "parentId", "children", Category.class);

        return (tree).stream().toList();
    }

    private List<Category> buildTree(List<Category> listCategorys) {

        return null;
    }
    
    /**
     * 获取商品分类表单数据
     *
     * @param id 商品分类ID
     * @return
     */
    @Override
    public Category getCategoryData(Long id) {
        Category entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增商品分类
     *
     * @param formData 商品分类表单对象
     * @return
     */
    @Override
    public boolean saveCategory(Category formData) {

        return this.save(formData);
    }
    
    /**
     * 更新商品分类
     *
     * @param id   商品分类ID
     * @param formData 商品分类表单对象
     * @return
     */
    @Override
    public boolean updateCategory(Long id,Category formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除商品分类
     *
     * @param ids 商品分类ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCategorys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品分类数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

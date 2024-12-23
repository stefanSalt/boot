package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.model.entity.Promotion;
import com.lee.warehouse.system.mapper.PromotionMapper;
import com.lee.warehouse.system.service.PromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
/**
 * 促销活动服务实现类
 *
 * @author baomidou
 * @since 2024-11-27
 */
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements PromotionService {


    /**
    * 获取促销活动分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Promotion>} 促销活动分页列表
    */
    @Override
    public IPage<Promotion> listPagedPromotions(Promotion queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Promotion> page = new Page<>(pageNum, pageSize);


    
        // 查询数据
        Page<Promotion> boPage = this.baseMapper.listPagedPromotions(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取促销活动表单数据
     *
     * @param id 促销活动ID
     * @return
     */
    @Override
    public Promotion getPromotionData(Long id) {
        Promotion entity = this.getById(id);
        entity.setProductIds(baseMapper.getProductIdsOfPromotion(id));
        return entity;
    }
    
    /**
     * 新增促销活动
     *
     * @param formData 促销活动表单对象
     * @return
     */
    @Override
    public boolean savePromotion(Promotion formData) {

        boolean save = this.save(formData);
        this.baseMapper.addProductsToPromotion(Long.valueOf(formData.getId()), formData.getProductIds());
        return save;
    }
    
    /**
     * 更新促销活动
     *
     * @param id   促销活动ID
     * @param formData 促销活动表单对象
     * @return
     */
    @Transactional
    @Override
    public boolean updatePromotion(Long id,Promotion formData) {
        this.baseMapper.removeProductsFromPromotion(id);
        this.baseMapper.addProductsToPromotion(id, formData.getProductIds());
        return this.updateById(formData);
    }
    
    /**
     * 删除促销活动
     *
     * @param ids 促销活动ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deletePromotions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的促销活动数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

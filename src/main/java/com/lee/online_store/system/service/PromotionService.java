package com.lee.online_store.system.service;

import com.lee.online_store.system.model.entity.Promotion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.online_store.system.model.entity.Promotion;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 促销活动 服务类
 *
 * @author baomidou
 * @since 2024-11-27
 */
public interface PromotionService extends IService<Promotion> {


    /**
     *促销活动分页列表
     *
     * @return
     */
    IPage<Promotion> listPagedPromotions(Promotion queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取促销活动表单数据
     *
     * @param id 促销活动ID
     * @return
     */
     Promotion getPromotionData(Long id);


    /**
     * 新增促销活动
     *
     * @param formData 促销活动表单对象
     * @return
     */
    boolean savePromotion(Promotion formData);

    /**
     * 修改促销活动
     *
     * @param id   促销活动ID
     * @param formData 促销活动表单对象
     * @return
     */
    boolean updatePromotion(Long id, Promotion formData);


    /**
     * 删除促销活动
     *
     * @param ids 促销活动ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deletePromotions(String ids);

}

package com.lee.system.service;

import com.lee.system.model.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.Activity;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 活动表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface ActivityService extends IService<Activity> {


    /**
     *活动表分页列表
     *
     * @return
     */
    IPage<Activity> listPagedActivitys(Activity queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取活动表表单数据
     *
     * @param id 活动表ID
     * @return
     */
     Activity getActivityData(Long id);


    /**
     * 新增活动表
     *
     * @param formData 活动表表单对象
     * @return
     */
    boolean saveActivity(Activity formData);

    /**
     * 修改活动表
     *
     * @param id   活动表ID
     * @param formData 活动表表单对象
     * @return
     */
    boolean updateActivity(Long id, Activity formData);


    /**
     * 删除活动表
     *
     * @param ids 活动表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteActivitys(String ids);

}

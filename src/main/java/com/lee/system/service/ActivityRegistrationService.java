package com.lee.system.service;

import com.lee.system.model.entity.ActivityRegistration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.ActivityRegistration;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 活动报名表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface ActivityRegistrationService extends IService<ActivityRegistration> {


    /**
     *活动报名表分页列表
     *
     * @return
     */
    IPage<ActivityRegistration> listPagedActivityRegistrations(ActivityRegistration queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取活动报名表表单数据
     *
     * @param id 活动报名表ID
     * @return
     */
     ActivityRegistration getActivityRegistrationData(Long id);


    /**
     * 新增活动报名表
     *
     * @param formData 活动报名表表单对象
     * @return
     */
    boolean saveActivityRegistration(ActivityRegistration formData);

    /**
     * 修改活动报名表
     *
     * @param id   活动报名表ID
     * @param formData 活动报名表表单对象
     * @return
     */
    boolean updateActivityRegistration(Long id, ActivityRegistration formData);


    /**
     * 删除活动报名表
     *
     * @param ids 活动报名表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteActivityRegistrations(String ids);

}

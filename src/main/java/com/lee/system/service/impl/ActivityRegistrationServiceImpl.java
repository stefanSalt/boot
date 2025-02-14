package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.ActivityRegistration;
import com.lee.system.mapper.ActivityRegistrationMapper;
import com.lee.system.service.ActivityRegistrationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.ActivityRegistration;

import java.util.Arrays;
import java.util.List;
/**
 * 活动报名表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class ActivityRegistrationServiceImpl extends ServiceImpl<ActivityRegistrationMapper, ActivityRegistration> implements ActivityRegistrationService {


    /**
    * 获取活动报名表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<ActivityRegistration>} 活动报名表分页列表
    */
    @Override
    public IPage<ActivityRegistration> listPagedActivityRegistrations(ActivityRegistration queryParams, Integer pageNum, Integer pageSize) {
    

        Page<ActivityRegistration> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<ActivityRegistration> boPage = this.baseMapper.listPagedActivityRegistrations(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取活动报名表表单数据
     *
     * @param id 活动报名表ID
     * @return
     */
    @Override
    public ActivityRegistration getActivityRegistrationData(Long id) {
        ActivityRegistration entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增活动报名表
     *
     * @param formData 活动报名表表单对象
     * @return
     */
    @Override
    public boolean saveActivityRegistration(ActivityRegistration formData) {

        return this.save(formData);
    }
    
    /**
     * 更新活动报名表
     *
     * @param id   活动报名表ID
     * @param formData 活动报名表表单对象
     * @return
     */
    @Override
    public boolean updateActivityRegistration(Long id,ActivityRegistration formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除活动报名表
     *
     * @param ids 活动报名表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteActivityRegistrations(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的活动报名表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

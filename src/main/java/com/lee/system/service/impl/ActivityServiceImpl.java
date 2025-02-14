package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Activity;
import com.lee.system.mapper.ActivityMapper;
import com.lee.system.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.Activity;

import java.util.Arrays;
import java.util.List;
/**
 * 活动表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {


    /**
    * 获取活动表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Activity>} 活动表分页列表
    */
    @Override
    public IPage<Activity> listPagedActivitys(Activity queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Activity> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Activity> boPage = this.baseMapper.listPagedActivitys(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取活动表表单数据
     *
     * @param id 活动表ID
     * @return
     */
    @Override
    public Activity getActivityData(Long id) {
        Activity entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增活动表
     *
     * @param formData 活动表表单对象
     * @return
     */
    @Override
    public boolean saveActivity(Activity formData) {

        return this.save(formData);
    }
    
    /**
     * 更新活动表
     *
     * @param id   活动表ID
     * @param formData 活动表表单对象
     * @return
     */
    @Override
    public boolean updateActivity(Long id,Activity formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除活动表
     *
     * @param ids 活动表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteActivitys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的活动表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

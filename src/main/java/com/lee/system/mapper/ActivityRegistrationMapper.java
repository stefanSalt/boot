package com.lee.system.mapper;

import com.lee.system.model.entity.ActivityRegistration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.ActivityRegistration;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动报名表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-02-10
 */

@Mapper
public interface ActivityRegistrationMapper extends BaseMapper<ActivityRegistration> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<ActivityRegistration> listPagedActivityRegistrations(Page<ActivityRegistration> page, ActivityRegistration queryParams);

}

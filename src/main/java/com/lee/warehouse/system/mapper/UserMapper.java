package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-25
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<User> listPagedUsers(Page<User> page, User queryParams);

}

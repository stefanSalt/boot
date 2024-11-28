package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-07
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Role> listPagedRoles(Page<Role> page, Role queryParams);

}

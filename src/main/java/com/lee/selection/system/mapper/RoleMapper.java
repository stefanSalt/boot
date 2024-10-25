package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.RoleBO;
import com.lee.selection.system.model.query.RolePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-24
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
    Page<RoleBO> listPagedRoles(Page<RoleBO> page, RolePageQuery queryParams);

}

package com.lee.selection.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.AdminBO;
import com.lee.selection.system.model.entity.Admin;
import com.lee.selection.system.model.query.AdminPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-14
 */

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<AdminBO> listPagedAdmins(Page<AdminBO> page,@Param("queryParams") AdminPageQuery queryParams);

}

package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包含  管理员  教师  学生等 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-24
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

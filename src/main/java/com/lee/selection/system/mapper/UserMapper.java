package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.UserBO;
import com.lee.selection.system.model.option.UserOption;
import com.lee.selection.system.model.query.UserPageQuery;
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
    Page<UserBO> listPagedUsers(Page<UserBO> page, UserPageQuery queryParams);

    List<UserOption> getTeachersByCourseId(Integer courseId);
}

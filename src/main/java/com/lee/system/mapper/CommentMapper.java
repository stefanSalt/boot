package com.lee.system.mapper;

import com.lee.system.model.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-02-10
 */

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Comment> listPagedComments(Page<Comment> page, Comment queryParams);

}

package com.lee.system.service;

import com.lee.system.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.Comment;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 评论表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface CommentService extends IService<Comment> {


    /**
     *评论表分页列表
     *
     * @return
     */
    IPage<Comment> listPagedComments(Comment queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取评论表表单数据
     *
     * @param id 评论表ID
     * @return
     */
     Comment getCommentData(Long id);


    /**
     * 新增评论表
     *
     * @param formData 评论表表单对象
     * @return
     */
    boolean saveComment(Comment formData);

    /**
     * 修改评论表
     *
     * @param id   评论表ID
     * @param formData 评论表表单对象
     * @return
     */
    boolean updateComment(Long id, Comment formData);


    /**
     * 删除评论表
     *
     * @param ids 评论表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteComments(String ids);

}

package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Comment;
import com.lee.system.mapper.CommentMapper;
import com.lee.system.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.Comment;

import java.util.Arrays;
import java.util.List;
/**
 * 评论表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    /**
    * 获取评论表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Comment>} 评论表分页列表
    */
    @Override
    public IPage<Comment> listPagedComments(Comment queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Comment> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Comment> boPage = this.baseMapper.listPagedComments(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取评论表表单数据
     *
     * @param id 评论表ID
     * @return
     */
    @Override
    public Comment getCommentData(Long id) {
        Comment entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增评论表
     *
     * @param formData 评论表表单对象
     * @return
     */
    @Override
    public boolean saveComment(Comment formData) {

        return this.save(formData);
    }
    
    /**
     * 更新评论表
     *
     * @param id   评论表ID
     * @param formData 评论表表单对象
     * @return
     */
    @Override
    public boolean updateComment(Long id,Comment formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除评论表
     *
     * @param ids 评论表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteComments(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的评论表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

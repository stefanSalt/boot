package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.mapper.UserMapper;
import com.lee.online_store.system.model.entity.Review;
import com.lee.online_store.system.mapper.ReviewMapper;
import com.lee.online_store.system.model.entity.User;
import com.lee.online_store.system.service.ReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.online_store.system.model.entity.Review;

import java.util.Arrays;
import java.util.List;
/**
 * 评论服务实现类
 *
 * @author baomidou
 * @since 2024-12-05
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    private final UserMapper userMapper;


    /**
    * 获取评论分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Review>} 评论分页列表
    */
    @Override
    public IPage<Review> listPagedReviews(Review queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Review> page = new Page<>(pageNum, pageSize);

    
        // 查询数据
        Page<Review> boPage = this.baseMapper.listPagedReviews(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取评论表单数据
     *
     * @param id 评论ID
     * @return
     */
    @Override
    public Review getReviewData(Long id) {
        Review entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增评论
     *
     * @param formData 评论表单对象
     * @return
     */
    @Override
    public boolean saveReview(Review formData) {
        User user = userMapper.selectById(formData.getUserId());
        formData.setAuthorName(user.getNickname());
        formData.setAuthorAvatar(user.getAvatar());
        return this.save(formData);
    }
    
    /**
     * 更新评论
     *
     * @param id   评论ID
     * @param formData 评论表单对象
     * @return
     */
    @Override
    public boolean updateReview(Long id,Review formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除评论
     *
     * @param ids 评论ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteReviews(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的评论数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    /**
     * 评论列表
     *
     * @param queryParams 查询参数
     * @return
     */
    @Override
    public List<Review> listReviews(Review queryParams) {
        List<Review> result = this.baseMapper.listReviews(queryParams);
        List<Review> rootList = result.stream().filter(item -> item.getParentId() == 0).toList();
        rootList.forEach(item -> {
            item.setChildren(
                    result.stream().
                            filter(child -> child.getParentId() != 0 && child.getParentId().equals(item.getId()))
                            .sorted((o1, o2)-> o2.getCreateTime().compareTo(o1.getCreateTime()))
                            .toList());
        });
        return rootList;
    }
}

package com.lee.online_store.system.service;

import com.lee.online_store.system.model.entity.Review;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.online_store.system.model.entity.Review;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 评论 服务类
 *
 * @author baomidou
 * @since 2024-12-05
 */
public interface ReviewService extends IService<Review> {


    /**
     *评论分页列表
     *
     * @return
     */
    IPage<Review> listPagedReviews(Review queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取评论表单数据
     *
     * @param id 评论ID
     * @return
     */
     Review getReviewData(Long id);


    /**
     * 新增评论
     *
     * @param formData 评论表单对象
     * @return
     */
    boolean saveReview(Review formData);

    /**
     * 修改评论
     *
     * @param id   评论ID
     * @param formData 评论表单对象
     * @return
     */
    boolean updateReview(Long id, Review formData);


    /**
     * 删除评论
     *
     * @param ids 评论ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteReviews(String ids);

    List<Review> listReviews(Review queryParams);
}

package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.lecture.system.model.entity.Feedback;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 讲座评价表 服务类
 *
 * @author baomidou
 * @since 2025-01-08
 */
public interface FeedbackService extends IService<Feedback> {


    /**
     *讲座评价表分页列表
     *
     * @return
     */
    IPage<Feedback> listPagedFeedbacks(Feedback queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取讲座评价表表单数据
     *
     * @param id 讲座评价表ID
     * @return
     */
     Feedback getFeedbackData(Long id);


    /**
     * 新增讲座评价表
     *
     * @param formData 讲座评价表表单对象
     * @return
     */
    boolean saveFeedback(Feedback formData);

    /**
     * 修改讲座评价表
     *
     * @param id   讲座评价表ID
     * @param formData 讲座评价表表单对象
     * @return
     */
    boolean updateFeedback(Long id, Feedback formData);


    /**
     * 删除讲座评价表
     *
     * @param ids 讲座评价表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteFeedbacks(String ids);

}

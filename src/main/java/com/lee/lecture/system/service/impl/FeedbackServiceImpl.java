package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.Feedback;
import com.lee.lecture.system.mapper.FeedbackMapper;
import com.lee.lecture.system.service.FeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.lecture.system.model.entity.Feedback;

import java.util.Arrays;
import java.util.List;
/**
 * 讲座评价表服务实现类
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {


    /**
    * 获取讲座评价表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Feedback>} 讲座评价表分页列表
    */
    @Override
    public IPage<Feedback> listPagedFeedbacks(Feedback queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Feedback> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Feedback> boPage = this.baseMapper.listPagedFeedbacks(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取讲座评价表表单数据
     *
     * @param id 讲座评价表ID
     * @return
     */
    @Override
    public Feedback getFeedbackData(Long id) {
        Feedback entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增讲座评价表
     *
     * @param formData 讲座评价表表单对象
     * @return
     */
    @Override
    public boolean saveFeedback(Feedback formData) {

        return this.save(formData);
    }
    
    /**
     * 更新讲座评价表
     *
     * @param id   讲座评价表ID
     * @param formData 讲座评价表表单对象
     * @return
     */
    @Override
    public boolean updateFeedback(Long id,Feedback formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除讲座评价表
     *
     * @param ids 讲座评价表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteFeedbacks(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的讲座评价表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

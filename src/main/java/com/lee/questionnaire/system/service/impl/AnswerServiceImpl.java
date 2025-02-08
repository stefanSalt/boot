package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.Answer;
import com.lee.questionnaire.system.mapper.AnswerMapper;
import com.lee.questionnaire.system.service.AnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.Answer;

import java.util.Arrays;
import java.util.List;
/**
 * 答案表服务实现类
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {


    /**
    * 获取答案表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Answer>} 答案表分页列表
    */
    @Override
    public IPage<Answer> listPagedAnswers(Answer queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Answer> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Answer> boPage = this.baseMapper.listPagedAnswers(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取答案表表单数据
     *
     * @param id 答案表ID
     * @return
     */
    @Override
    public Answer getAnswerData(Long id) {
        Answer entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增答案表
     *
     * @param formData 答案表表单对象
     * @return
     */
    @Override
    public boolean saveAnswer(Answer formData) {

        return this.save(formData);
    }
    
    /**
     * 更新答案表
     *
     * @param id   答案表ID
     * @param formData 答案表表单对象
     * @return
     */
    @Override
    public boolean updateAnswer(Long id,Answer formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除答案表
     *
     * @param ids 答案表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteAnswers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的答案表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

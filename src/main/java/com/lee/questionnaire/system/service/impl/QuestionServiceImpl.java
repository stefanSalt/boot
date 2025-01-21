package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.Question;
import com.lee.questionnaire.system.mapper.QuestionMapper;
import com.lee.questionnaire.system.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.Question;

import java.util.Arrays;
import java.util.List;
/**
 * 问题表服务实现类
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {


    /**
    * 获取问题表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Question>} 问题表分页列表
    */
    @Override
    public IPage<Question> listPagedQuestions(Question queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Question> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Question> boPage = this.baseMapper.listPagedQuestions(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取问题表表单数据
     *
     * @param id 问题表ID
     * @return
     */
    @Override
    public Question getQuestionData(Long id) {
        Question entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增问题表
     *
     * @param formData 问题表表单对象
     * @return
     */
    @Override
    public boolean saveQuestion(Question formData) {

        return this.save(formData);
    }
    
    /**
     * 更新问题表
     *
     * @param id   问题表ID
     * @param formData 问题表表单对象
     * @return
     */
    @Override
    public boolean updateQuestion(Long id,Question formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除问题表
     *
     * @param ids 问题表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteQuestions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的问题表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

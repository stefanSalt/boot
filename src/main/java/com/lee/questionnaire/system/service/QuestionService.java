package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.Question;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 问题表 服务类
 *
 * @author baomidou
 * @since 2025-01-21
 */
public interface QuestionService extends IService<Question> {


    /**
     *问题表分页列表
     *
     * @return
     */
    IPage<Question> listPagedQuestions(Question queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取问题表表单数据
     *
     * @param id 问题表ID
     * @return
     */
     Question getQuestionData(Long id);


    /**
     * 新增问题表
     *
     * @param formData 问题表表单对象
     * @return
     */
    boolean saveQuestion(Question formData);

    /**
     * 修改问题表
     *
     * @param id   问题表ID
     * @param formData 问题表表单对象
     * @return
     */
    boolean updateQuestion(Long id, Question formData);


    /**
     * 删除问题表
     *
     * @param ids 问题表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteQuestions(String ids);

}

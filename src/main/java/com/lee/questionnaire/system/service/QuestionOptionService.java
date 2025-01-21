package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 选项表 服务类
 *
 * @author baomidou
 * @since 2025-01-21
 */
public interface QuestionOptionService extends IService<QuestionOption> {


    /**
     *选项表分页列表
     *
     * @return
     */
    IPage<QuestionOption> listPagedQuestionOptions(QuestionOption queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取选项表表单数据
     *
     * @param id 选项表ID
     * @return
     */
     QuestionOption getQuestionOptionData(Long id);


    /**
     * 新增选项表
     *
     * @param formData 选项表表单对象
     * @return
     */
    boolean saveQuestionOption(QuestionOption formData);

    /**
     * 修改选项表
     *
     * @param id   选项表ID
     * @param formData 选项表表单对象
     * @return
     */
    boolean updateQuestionOption(Long id, QuestionOption formData);


    /**
     * 删除选项表
     *
     * @param ids 选项表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteQuestionOptions(String ids);

}

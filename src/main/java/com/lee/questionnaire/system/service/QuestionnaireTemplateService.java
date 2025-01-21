package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 问卷模板表 服务类
 *
 * @author baomidou
 * @since 2025-01-21
 */
public interface QuestionnaireTemplateService extends IService<QuestionnaireTemplate> {


    /**
     *问卷模板表分页列表
     *
     * @return
     */
    IPage<QuestionnaireTemplate> listPagedQuestionnaireTemplates(QuestionnaireTemplate queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取问卷模板表表单数据
     *
     * @param id 问卷模板表ID
     * @return
     */
     QuestionnaireTemplate getQuestionnaireTemplateData(Long id);


    /**
     * 新增问卷模板表
     *
     * @param formData 问卷模板表表单对象
     * @return
     */
    boolean saveQuestionnaireTemplate(QuestionnaireTemplate formData);

    /**
     * 修改问卷模板表
     *
     * @param id   问卷模板表ID
     * @param formData 问卷模板表表单对象
     * @return
     */
    boolean updateQuestionnaireTemplate(Long id, QuestionnaireTemplate formData);


    /**
     * 删除问卷模板表
     *
     * @param ids 问卷模板表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteQuestionnaireTemplates(String ids);

}

package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.Questionnaire;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 问卷表 服务类
 *
 * @author baomidou
 * @since 2025-01-21
 */
public interface QuestionnaireService extends IService<Questionnaire> {


    /**
     *问卷表分页列表
     *
     * @return
     */
    IPage<Questionnaire> listPagedQuestionnaires(Questionnaire queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取问卷表表单数据
     *
     * @param id 问卷表ID
     * @return
     */
     Questionnaire getQuestionnaireData(Long id);


    /**
     * 新增问卷表
     *
     * @param formData 问卷表表单对象
     * @return
     */
    boolean saveQuestionnaire(Questionnaire formData);

    /**
     * 修改问卷表
     *
     * @param id   问卷表ID
     * @param formData 问卷表表单对象
     * @return
     */
    boolean updateQuestionnaire(Long id, Questionnaire formData);


    /**
     * 删除问卷表
     *
     * @param ids 问卷表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteQuestionnaires(String ids);

}

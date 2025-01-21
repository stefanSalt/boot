package com.lee.questionnaire.system.mapper;

import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问卷模板表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-21
 */

@Mapper
public interface QuestionnaireTemplateMapper extends BaseMapper<QuestionnaireTemplate> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<QuestionnaireTemplate> listPagedQuestionnaireTemplates(Page<QuestionnaireTemplate> page, QuestionnaireTemplate queryParams);

}

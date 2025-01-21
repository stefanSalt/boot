package com.lee.questionnaire.system.mapper;

import com.lee.questionnaire.system.model.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 问题表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-21
 */

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Question> listPagedQuestions(Page<Question> page, Question queryParams);

    void saveBatch(List<Question> questions);

    void deleteByQuestionnaireId(Long id);
}

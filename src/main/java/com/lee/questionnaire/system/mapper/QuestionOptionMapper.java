package com.lee.questionnaire.system.mapper;

import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.QuestionOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 选项表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-21
 */

@Mapper
public interface QuestionOptionMapper extends BaseMapper<QuestionOption> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<QuestionOption> listPagedQuestionOptions(Page<QuestionOption> page, QuestionOption queryParams);

    void saveBatch(List<QuestionOption> list);

    void deleteByQuestionId(Integer id);

    void deleteByQuestionIds(List<Integer> ids);
}

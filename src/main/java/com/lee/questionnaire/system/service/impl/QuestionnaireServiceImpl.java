package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.mapper.QuestionMapper;
import com.lee.questionnaire.system.mapper.QuestionOptionMapper;
import com.lee.questionnaire.system.model.entity.Question;
import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.lee.questionnaire.system.model.entity.Questionnaire;
import com.lee.questionnaire.system.mapper.QuestionnaireMapper;
import com.lee.questionnaire.system.service.QuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.Questionnaire;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 问卷表服务实现类
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

    private final QuestionMapper questionMapper;
    private final QuestionOptionMapper questionOptionMapper;

    /**
     * 获取问卷表分页列表
     *
     * @param queryParams 查询参数
     * @param pageNum     页号
     * @param pageSize    页大小
     * @return {@link IPage<Questionnaire>} 问卷表分页列表
     */
    @Override
    public IPage<Questionnaire> listPagedQuestionnaires(Questionnaire queryParams, Integer pageNum, Integer pageSize) {


        Page<Questionnaire> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Questionnaire> boPage = this.baseMapper.listPagedQuestionnaires(page, queryParams);

        return boPage;
    }

    /**
     * 获取问卷表表单数据
     *
     * @param id 问卷表ID
     * @return
     */
    @Override
    public Questionnaire getQuestionnaireData(Long id) {
        Questionnaire entity = this.baseMapper.getQuestionnaireData(id);

        return entity;
    }

    /**
     * 新增问卷表
     *
     * @param formData 问卷表表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveQuestionnaire(Questionnaire formData) {

        boolean save = this.save(formData);
        if (save) {
            // 保存新数据
            List<Question> questions = formData.getQuestions();
            questions.forEach(question -> question.setQuestionnaireId(formData.getId()));
            questionMapper.saveBatch(questions);
            questions.forEach(question -> {
                List<QuestionOption> options = question.getOptions();
                options.forEach(questionOption -> questionOption.setQuestionId(question.getId()));
                if (!options.isEmpty()){
                    questionOptionMapper.saveBatch(options);
                }

            });

        }
        return save;
    }

    /**
     * 更新问卷表
     *
     * @param id       问卷表ID
     * @param formData 问卷表表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateQuestionnaire(Long id, Questionnaire formData) {
        boolean b = this.updateById(formData);
        if (b) {
            questionMapper.deleteByQuestionnaireId(id);
            List<Question> questions = formData.getQuestions();
            List<Integer> ids = questions.stream()
                    .map(Question::getId)
                    .filter(Objects::nonNull)
                    .toList();
            if (!ids.isEmpty()) {
                questionOptionMapper.deleteByQuestionIds(ids);
            }
            questions.forEach(question -> {
                question.setQuestionnaireId(formData.getId());
                question.setId(null);
            });
            questionMapper.saveBatch(questions);
            questions.forEach(question -> {
                List<QuestionOption> options = question.getOptions();
                options.forEach(questionOption -> {
                    questionOption.setQuestionId(question.getId());
                    questionOption.setId(null);
                });
                if (!options.isEmpty()){
                    questionOptionMapper.saveBatch(options);
                }
            });
        }
        return b;
    }

    /**
     * 删除问卷表
     *
     * @param ids 问卷表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteQuestionnaires(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的问卷表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        return this.removeByIds(idList);
    }


}

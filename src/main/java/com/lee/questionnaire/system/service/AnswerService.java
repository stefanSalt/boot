package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.Answer;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 答案表 服务类
 *
 * @author baomidou
 * @since 2025-02-07
 */
public interface AnswerService extends IService<Answer> {


    /**
     *答案表分页列表
     *
     * @return
     */
    IPage<Answer> listPagedAnswers(Answer queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取答案表表单数据
     *
     * @param id 答案表ID
     * @return
     */
     Answer getAnswerData(Long id);


    /**
     * 新增答案表
     *
     * @param formData 答案表表单对象
     * @return
     */
    boolean saveAnswer(Answer formData);

    /**
     * 修改答案表
     *
     * @param id   答案表ID
     * @param formData 答案表表单对象
     * @return
     */
    boolean updateAnswer(Long id, Answer formData);


    /**
     * 删除答案表
     *
     * @param ids 答案表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteAnswers(String ids);

}

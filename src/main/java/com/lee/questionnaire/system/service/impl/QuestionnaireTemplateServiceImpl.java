package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import com.lee.questionnaire.system.mapper.QuestionnaireTemplateMapper;
import com.lee.questionnaire.system.service.QuestionnaireTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;

import java.util.Arrays;
import java.util.List;
/**
 * 问卷模板表服务实现类
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
@RequiredArgsConstructor
public class QuestionnaireTemplateServiceImpl extends ServiceImpl<QuestionnaireTemplateMapper, QuestionnaireTemplate> implements QuestionnaireTemplateService {


    /**
    * 获取问卷模板表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<QuestionnaireTemplate>} 问卷模板表分页列表
    */
    @Override
    public IPage<QuestionnaireTemplate> listPagedQuestionnaireTemplates(QuestionnaireTemplate queryParams, Integer pageNum, Integer pageSize) {
    

        Page<QuestionnaireTemplate> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<QuestionnaireTemplate> boPage = this.baseMapper.listPagedQuestionnaireTemplates(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取问卷模板表表单数据
     *
     * @param id 问卷模板表ID
     * @return
     */
    @Override
    public QuestionnaireTemplate getQuestionnaireTemplateData(Long id) {
        QuestionnaireTemplate entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增问卷模板表
     *
     * @param formData 问卷模板表表单对象
     * @return
     */
    @Override
    public boolean saveQuestionnaireTemplate(QuestionnaireTemplate formData) {

        return this.save(formData);
    }
    
    /**
     * 更新问卷模板表
     *
     * @param id   问卷模板表ID
     * @param formData 问卷模板表表单对象
     * @return
     */
    @Override
    public boolean updateQuestionnaireTemplate(Long id,QuestionnaireTemplate formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除问卷模板表
     *
     * @param ids 问卷模板表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteQuestionnaireTemplates(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的问卷模板表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.lee.questionnaire.system.mapper.QuestionOptionMapper;
import com.lee.questionnaire.system.service.QuestionOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.QuestionOption;

import java.util.Arrays;
import java.util.List;
/**
 * 选项表服务实现类
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl extends ServiceImpl<QuestionOptionMapper, QuestionOption> implements QuestionOptionService {


    /**
    * 获取选项表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<QuestionOption>} 选项表分页列表
    */
    @Override
    public IPage<QuestionOption> listPagedQuestionOptions(QuestionOption queryParams, Integer pageNum, Integer pageSize) {
    

        Page<QuestionOption> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<QuestionOption> boPage = this.baseMapper.listPagedQuestionOptions(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取选项表表单数据
     *
     * @param id 选项表ID
     * @return
     */
    @Override
    public QuestionOption getQuestionOptionData(Long id) {
        QuestionOption entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增选项表
     *
     * @param formData 选项表表单对象
     * @return
     */
    @Override
    public boolean saveQuestionOption(QuestionOption formData) {

        return this.save(formData);
    }
    
    /**
     * 更新选项表
     *
     * @param id   选项表ID
     * @param formData 选项表表单对象
     * @return
     */
    @Override
    public boolean updateQuestionOption(Long id,QuestionOption formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除选项表
     *
     * @param ids 选项表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteQuestionOptions(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的选项表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

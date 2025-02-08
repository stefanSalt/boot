package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.mapper.AnswerMapper;
import com.lee.questionnaire.system.model.entity.Answer;
import com.lee.questionnaire.system.model.entity.Response;
import com.lee.questionnaire.system.mapper.ResponseMapper;
import com.lee.questionnaire.system.service.ResponseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.Response;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
/**
 * 答卷表服务实现类
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Service
@RequiredArgsConstructor
public class ResponseServiceImpl extends ServiceImpl<ResponseMapper, Response> implements ResponseService {

    private final AnswerMapper answerMapper;

    /**
    * 获取答卷表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Response>} 答卷表分页列表
    */
    @Override
    public IPage<Response> listPagedResponses(Response queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Response> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Response> boPage = this.baseMapper.listPagedResponses(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取答卷表表单数据
     *
     * @param id 答卷表ID
     * @return
     */
    @Override
    public Response getResponseData(Long id) {
        Response entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增答卷表
     *
     * @param formData 答卷表表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveResponse(Response formData) {
        List<Answer> answers = formData.getAnswers();

        boolean save = this.save(formData);
        if (save) {
            answers.forEach(answer -> {
                answer.setResponseId(formData.getId());
                answerMapper.insert(answer);
            });
        }
        return save;
    }
    
    /**
     * 更新答卷表
     *
     * @param id   答卷表ID
     * @param formData 答卷表表单对象
     * @return
     */
    @Override
    public boolean updateResponse(Long id,Response formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除答卷表
     *
     * @param ids 答卷表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteResponses(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的答卷表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

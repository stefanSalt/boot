package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.Response;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 答卷表 服务类
 *
 * @author baomidou
 * @since 2025-02-07
 */
public interface ResponseService extends IService<Response> {


    /**
     *答卷表分页列表
     *
     * @return
     */
    IPage<Response> listPagedResponses(Response queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取答卷表表单数据
     *
     * @param id 答卷表ID
     * @return
     */
     Response getResponseData(Long id);


    /**
     * 新增答卷表
     *
     * @param formData 答卷表表单对象
     * @return
     */
    boolean saveResponse(Response formData);

    /**
     * 修改答卷表
     *
     * @param id   答卷表ID
     * @param formData 答卷表表单对象
     * @return
     */
    boolean updateResponse(Long id, Response formData);


    /**
     * 删除答卷表
     *
     * @param ids 答卷表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteResponses(String ids);

}

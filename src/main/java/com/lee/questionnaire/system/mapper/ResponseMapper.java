package com.lee.questionnaire.system.mapper;

import com.lee.questionnaire.system.model.entity.Response;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.Response;
import org.apache.ibatis.annotations.Mapper;

/**
 * 答卷表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-02-07
 */

@Mapper
public interface ResponseMapper extends BaseMapper<Response> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Response> listPagedResponses(Page<Response> page, Response queryParams);

}

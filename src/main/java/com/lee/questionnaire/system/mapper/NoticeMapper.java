package com.lee.questionnaire.system.mapper;

import com.lee.questionnaire.system.model.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-20
 */

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Notice> listPagedNotices(Page<Notice> page, Notice queryParams);

}

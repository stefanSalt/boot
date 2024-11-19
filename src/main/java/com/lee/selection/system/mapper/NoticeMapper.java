package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-14
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

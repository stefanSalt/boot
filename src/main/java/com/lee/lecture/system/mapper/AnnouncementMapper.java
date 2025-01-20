package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-11
 */

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Announcement> listPagedAnnouncements(Page<Announcement> page, Announcement queryParams);

}

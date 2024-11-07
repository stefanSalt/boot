//package com.lee.selection.system.mapper;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.lee.selection.system.model.bo.NoticeBO;
//import com.lee.selection.system.model.entity.Notice;
//import com.lee.selection.system.model.query.NoticePageQuery;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
///**
// * 公告信息 Mapper 接口
// *
// * @author baomidou
// * @since 2024-10-16
// */
//
//@Mapper
//public interface NoticeMapper extends BaseMapper<Notice> {
//
//    /**
//     * 获取用户分页列表
//     *
//     * @param page
//     * @param queryParams 查询参数
//     * @return
//     */
//    Page<NoticeBO> listPagedNotices(Page<NoticeBO> page,@Param("queryParams") NoticePageQuery queryParams);
//
//}

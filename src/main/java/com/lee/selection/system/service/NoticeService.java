package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Notice;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 公告信息 服务类
 *
 * @author baomidou
 * @since 2024-11-14
 */
public interface NoticeService extends IService<Notice> {


    /**
     *公告信息分页列表
     *
     * @return
     */
    IPage<Notice> listPagedNotices(Notice queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取公告信息表单数据
     *
     * @param id 公告信息ID
     * @return
     */
     Notice getNoticeData(Long id);


    /**
     * 新增公告信息
     *
     * @param formData 公告信息表单对象
     * @return
     */
    boolean saveNotice(Notice formData);

    /**
     * 修改公告信息
     *
     * @param id   公告信息ID
     * @param formData 公告信息表单对象
     * @return
     */
    boolean updateNotice(Long id, Notice formData);


    /**
     * 删除公告信息
     *
     * @param ids 公告信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNotices(String ids);

}

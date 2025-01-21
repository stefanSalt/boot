package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.questionnaire.system.model.entity.Notice;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 公告表 服务类
 *
 * @author baomidou
 * @since 2025-01-20
 */
public interface NoticeService extends IService<Notice> {


    /**
     *公告表分页列表
     *
     * @return
     */
    IPage<Notice> listPagedNotices(Notice queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取公告表表单数据
     *
     * @param id 公告表ID
     * @return
     */
     Notice getNoticeData(Long id);


    /**
     * 新增公告表
     *
     * @param formData 公告表表单对象
     * @return
     */
    boolean saveNotice(Notice formData);

    /**
     * 修改公告表
     *
     * @param id   公告表ID
     * @param formData 公告表表单对象
     * @return
     */
    boolean updateNotice(Long id, Notice formData);


    /**
     * 删除公告表
     *
     * @param ids 公告表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNotices(String ids);

}

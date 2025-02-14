package com.lee.system.service;

import com.lee.system.model.entity.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.Announcement;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 公告表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface AnnouncementService extends IService<Announcement> {


    /**
     *公告表分页列表
     *
     * @return
     */
    IPage<Announcement> listPagedAnnouncements(Announcement queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取公告表表单数据
     *
     * @param id 公告表ID
     * @return
     */
     Announcement getAnnouncementData(Long id);


    /**
     * 新增公告表
     *
     * @param formData 公告表表单对象
     * @return
     */
    boolean saveAnnouncement(Announcement formData);

    /**
     * 修改公告表
     *
     * @param id   公告表ID
     * @param formData 公告表表单对象
     * @return
     */
    boolean updateAnnouncement(Long id, Announcement formData);


    /**
     * 删除公告表
     *
     * @param ids 公告表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteAnnouncements(String ids);

}

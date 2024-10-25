package com.lee.selection.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Notice;
import com.lee.selection.system.model.form.NoticeForm;
import com.lee.selection.system.model.query.NoticePageQuery;
import com.lee.selection.system.model.vo.NoticePageVO;
import com.lee.selection.system.model.vo.NoticeVO;

/**
 * 公告信息 服务类
 *
 * @author baomidou
 * @since 2024-10-16
 */
public interface NoticeService extends IService<Notice> {


    /**
     *公告信息分页列表
     *
     * @return
     */
    IPage<NoticePageVO> listPagedNotices(NoticePageQuery queryParams);


    /**
     * 获取公告信息表单数据
     *
     * @param id 公告信息ID
     * @return
     */
     NoticeForm getNoticeFormData(Long id);


    /**
     * 新增公告信息
     *
     * @param formData 公告信息表单对象
     * @return
     */
    boolean saveNotice(NoticeForm formData);

    /**
     * 修改公告信息
     *
     * @param id   公告信息ID
     * @param formData 公告信息表单对象
     * @return
     */
    boolean updateNotice(Long id, NoticeForm formData);


    /**
     * 删除公告信息
     *
     * @param ids 公告信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNotices(String ids);

    NoticeVO getNoticeDetail(Long id);

    boolean publishNotice(Long id);

    boolean unPublishNotice(Long id);
}

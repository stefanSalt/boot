package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Message;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 留言 服务类
 *
 * @author baomidou
 * @since 2024-11-14
 */
public interface MessageService extends IService<Message> {


    /**
     *留言分页列表
     *
     * @return
     */
    IPage<Message> listPagedMessages(Message queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取留言表单数据
     *
     * @param id 留言ID
     * @return
     */
     Message getMessageData(Long id);


    /**
     * 新增留言
     *
     * @param formData 留言表单对象
     * @return
     */
    boolean saveMessage(Message formData);

    /**
     * 修改留言
     *
     * @param id   留言ID
     * @param formData 留言表单对象
     * @return
     */
    boolean updateMessage(Long id, Message formData);


    /**
     * 删除留言
     *
     * @param ids 留言ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteMessages(String ids);

}

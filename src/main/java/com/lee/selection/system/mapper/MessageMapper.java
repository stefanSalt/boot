package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-14
 */

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Message> listPagedMessages(Page<Message> page, Message queryParams);

}

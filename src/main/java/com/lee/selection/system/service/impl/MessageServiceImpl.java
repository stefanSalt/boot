package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Message;
import com.lee.selection.system.mapper.MessageMapper;
import com.lee.selection.system.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.entity.Message;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 留言服务实现类
 *
 * @author baomidou
 * @since 2024-11-14
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {


    /**
    * 获取留言分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Message>} 留言分页列表
    */
    @Override
    public IPage<Message> listPagedMessages(Message queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Message> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Message> boPage = this.baseMapper.listPagedMessages(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取留言表单数据
     *
     * @param id 留言ID
     * @return
     */
    @Override
    public Message getMessageData(Long id) {
        Message entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增留言
     *
     * @param formData 留言表单对象
     * @return
     */
    @Override
    public boolean saveMessage(Message formData) {

        return this.save(formData);
    }
    
    /**
     * 更新留言
     *
     * @param id   留言ID
     * @param formData 留言表单对象
     * @return
     */
    @Override
    public boolean updateMessage(Long id,Message formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除留言
     *
     * @param ids 留言ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteMessages(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的留言数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

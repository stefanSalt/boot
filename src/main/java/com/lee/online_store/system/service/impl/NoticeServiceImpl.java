package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.common.util.DateUtils;
import com.lee.online_store.system.model.entity.Notice;
import com.lee.online_store.system.mapper.NoticeMapper;
import com.lee.online_store.system.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.online_store.system.model.entity.Notice;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 公告信息服务实现类
 *
 * @author baomidou
 * @since 2024-11-14
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    /**
    * 获取公告信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Notice>} 公告信息分页列表
    */
    @Override
    public IPage<Notice> listPagedNotices(Notice queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Notice> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Notice> boPage = this.baseMapper.listPagedNotices(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取公告信息表单数据
     *
     * @param id 公告信息ID
     * @return
     */
    @Override
    public Notice getNoticeData(Long id) {
        Notice entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增公告信息
     *
     * @param formData 公告信息表单对象
     * @return
     */
    @Override
    public boolean saveNotice(Notice formData) {

        return this.save(formData);
    }
    
    /**
     * 更新公告信息
     *
     * @param id   公告信息ID
     * @param formData 公告信息表单对象
     * @return
     */
    @Override
    public boolean updateNotice(Long id,Notice formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除公告信息
     *
     * @param ids 公告信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteNotices(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的公告信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

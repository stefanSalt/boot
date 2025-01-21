package com.lee.questionnaire.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.questionnaire.system.model.entity.Notice;
import com.lee.questionnaire.system.mapper.NoticeMapper;
import com.lee.questionnaire.system.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.questionnaire.system.model.entity.Notice;

import java.util.Arrays;
import java.util.List;
/**
 * 公告表服务实现类
 *
 * @author baomidou
 * @since 2025-01-20
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    /**
    * 获取公告表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Notice>} 公告表分页列表
    */
    @Override
    public IPage<Notice> listPagedNotices(Notice queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Notice> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Notice> boPage = this.baseMapper.listPagedNotices(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取公告表表单数据
     *
     * @param id 公告表ID
     * @return
     */
    @Override
    public Notice getNoticeData(Long id) {
        Notice entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增公告表
     *
     * @param formData 公告表表单对象
     * @return
     */
    @Override
    public boolean saveNotice(Notice formData) {

        return this.save(formData);
    }
    
    /**
     * 更新公告表
     *
     * @param id   公告表ID
     * @param formData 公告表表单对象
     * @return
     */
    @Override
    public boolean updateNotice(Long id,Notice formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除公告表
     *
     * @param ids 公告表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteNotices(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的公告表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

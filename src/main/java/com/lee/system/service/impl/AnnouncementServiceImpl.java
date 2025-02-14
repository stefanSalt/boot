package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Announcement;
import com.lee.system.mapper.AnnouncementMapper;
import com.lee.system.service.AnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.Announcement;

import java.util.Arrays;
import java.util.List;
/**
 * 公告表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {


    /**
    * 获取公告表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Announcement>} 公告表分页列表
    */
    @Override
    public IPage<Announcement> listPagedAnnouncements(Announcement queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Announcement> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Announcement> boPage = this.baseMapper.listPagedAnnouncements(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取公告表表单数据
     *
     * @param id 公告表ID
     * @return
     */
    @Override
    public Announcement getAnnouncementData(Long id) {
        Announcement entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增公告表
     *
     * @param formData 公告表表单对象
     * @return
     */
    @Override
    public boolean saveAnnouncement(Announcement formData) {

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
    public boolean updateAnnouncement(Long id,Announcement formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除公告表
     *
     * @param ids 公告表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteAnnouncements(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的公告表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}

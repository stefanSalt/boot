package com.lee.reservation.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.reservation.common.exception.BusinessException;
import com.lee.reservation.common.util.DateUtils;
import com.lee.reservation.common.util.SystemUtils;
import com.lee.reservation.system.converter.NoticeConverter;
import com.lee.reservation.system.mapper.NoticeMapper;
import com.lee.reservation.system.model.bo.NoticeBO;
import com.lee.reservation.system.model.entity.Admin;
import com.lee.reservation.system.model.entity.Notice;
import com.lee.reservation.system.model.form.NoticeForm;
import com.lee.reservation.system.model.query.NoticePageQuery;
import com.lee.reservation.system.model.vo.NoticePageVO;
import com.lee.reservation.system.model.vo.NoticeVO;
import com.lee.reservation.system.service.AdminService;
import com.lee.reservation.system.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 公告信息服务实现类
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    private final NoticeConverter noticeConverter;
    private final AdminService adminService;

    /**
    * 获取公告信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<NoticePageVO>} 公告信息分页列表
    */
    @Override
    public IPage<NoticePageVO> listPagedNotices(NoticePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<NoticeBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<NoticeBO> boPage = this.baseMapper.listPagedNotices(page, queryParams);
        IPage<NoticePageVO> convert = boPage.convert(noticeConverter::toPageVo);

        // 实体转换
        return convert;
    }
    
    /**
     * 获取公告信息表单数据
     *
     * @param id 公告信息ID
     * @return
     */
    @Override
    public NoticeForm getNoticeFormData(Long id) {
        Notice entity = this.getById(id);
        return noticeConverter.toForm(entity);
    }
    
    /**
     * 新增公告信息
     *
     * @param formData 公告信息表单对象
     * @return
     */
    @Override
    public boolean saveNotice(NoticeForm formData) {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        Admin admin = adminService.getOne(adminQueryWrapper);

        formData.setPublisher(admin.getId());
        formData.setPublishTime(LocalDateTime.now());

        // 实体转换 form->entity
        Notice entity = noticeConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新公告信息
     *
     * @param id   公告信息ID
     * @param formData 公告信息表单对象
     * @return
     */
    @Override
    public boolean updateNotice(Long id,NoticeForm formData) {
        Notice entity = noticeConverter.toEntity(formData);
        return this.updateById(entity);
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

    @Override
    public NoticeVO getNoticeDetail(Long id) {
        Notice notice = this.getById(id);
        if (notice==null){
            throw new BusinessException("公告不存在");
        }
        return noticeConverter.toVo(notice);
    }

    @Override
    public boolean publishNotice(Long id) {
        Notice notice = this.getById(id);
        if (notice!=null){
            notice.setStatus(1);
            notice.setPublishTime(LocalDateTime.now());
            return this.updateById(notice);
        }
        return false;
    }

    @Override
    public boolean unPublishNotice(Long id) {
        Notice notice = this.getById(id);
        if (notice!=null){
            notice.setStatus(0);
            return this.updateById(notice);
        }
        return false;
    }
}

package com.lee.reservation.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.NoticeBO;
import com.lee.reservation.system.model.entity.Notice;
import com.lee.reservation.system.model.form.NoticeForm;
import com.lee.reservation.system.model.vo.NoticePageVO;
import com.lee.reservation.system.model.vo.NoticeVO;
import com.lee.reservation.system.service.AdminService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 公告信息转换器
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Mapper(componentModel = "spring",uses = {AdminService.class})
public interface NoticeConverter{

    @Mappings(
            @Mapping(target = "publisherName",  expression = "java(adminService.getAdminName(bo.getPublisher()))")
    )
    NoticePageVO toPageVo(NoticeBO bo);

    // 将publisher 转换为 publisherName

    Page<NoticePageVO> toPageVo(Page<NoticeBO> bo);

    NoticeForm toForm(Notice entity);

    @InheritInverseConfiguration(name = "toForm")
    Notice toEntity(NoticeForm entity);

    @Mappings(
            @Mapping(target = "publisherName",  expression = "java(adminService.getAdminName(entity.getPublisher()))")
    )
    NoticeVO toVo(Notice entity);


}
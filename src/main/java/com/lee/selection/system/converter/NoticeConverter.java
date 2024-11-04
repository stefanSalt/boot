package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.NoticeBO;
import com.lee.selection.system.model.entity.Notice;
import com.lee.selection.system.model.form.NoticeForm;
import com.lee.selection.system.model.vo.NoticePageVO;
import com.lee.selection.system.model.vo.NoticeVO;
import com.lee.selection.system.service.UserService;
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
@Mapper(componentModel = "spring",uses = {UserService.class})
public interface NoticeConverter{

    @Mappings(
            @Mapping(target = "publisherName",  expression = "java(userService.getUserName(bo.getPublisher()))")
    )
    NoticePageVO toPageVo(NoticeBO bo);

    // 将publisher 转换为 publisherName

    Page<NoticePageVO> toPageVo(Page<NoticeBO> bo);

    NoticeForm toForm(Notice entity);

    @InheritInverseConfiguration(name = "toForm")
    Notice toEntity(NoticeForm entity);

    @Mappings(
            @Mapping(target = "publisherName",  expression = "java(userService.getUserName(entity.getPublisher()))")
    )
    NoticeVO toVo(Notice entity);


}
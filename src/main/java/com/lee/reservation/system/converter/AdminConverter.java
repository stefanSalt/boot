package com.lee.reservation.system.converter;

import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.vo.AdminVO;
import com.lee.reservation.system.model.vo.ProfileVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.reservation.system.model.dto.AdminDTO;
import com.lee.reservation.system.model.entity.Admin;
import com.lee.reservation.system.model.vo.AdminPageVO;
import com.lee.reservation.system.model.form.AdminForm;
import com.lee.reservation.system.model.bo.AdminBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 管理员转换器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Mapper(componentModel = "spring")
public interface AdminConverter{

    AdminPageVO toPageVo(AdminBO bo);

    Page<AdminPageVO> toPageVo(Page<AdminBO> bo);

    AdminForm toForm(Admin entity);

    @InheritInverseConfiguration(name = "toForm")
    Admin toEntity(AdminForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    AdminVO toVo(Admin entity);

    ProfileVO toProfileVo(Admin entity);

    Admin toEntity(ProfileForm entity);
}
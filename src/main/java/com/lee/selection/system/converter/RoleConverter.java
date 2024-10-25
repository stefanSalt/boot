package com.lee.selection.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.dto.RoleDTO;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.model.vo.RolePageVO;
import com.lee.selection.system.model.form.RoleForm;
import com.lee.selection.system.model.bo.RoleBO;

/**
 * 角色信息表转换器
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Mapper(componentModel = "spring")
public interface RoleConverter{

    RolePageVO toPageVo(RoleBO bo);

    Page<RolePageVO> toPageVo(Page<RoleBO> bo);

    RoleForm toForm(Role entity);

    @InheritInverseConfiguration(name = "toForm")
    Role toEntity(RoleForm entity);
}
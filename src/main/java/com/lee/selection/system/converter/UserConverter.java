package com.lee.selection.system.converter;


import com.lee.selection.system.model.entity.Admin;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.model.vo.UserPageVO;
import com.lee.selection.system.model.form.UserForm;
import com.lee.selection.system.model.bo.UserBO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户转换器
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Mapper(componentModel = "spring",uses = {ConverterWorker.class})
public interface UserConverter {

    @Mappings(
            {
                    @Mapping(target = "genderLabel",expression =
                            "java(com.lee.selection.common.base.IBaseEnum.getLabelByValue(bo.getGender(), " +
                                    "com.lee.selection.system.enums.GenderEnum.class))"),
                    @Mapping(target = "clazzName", source = "clazzId", qualifiedByName = "getClazzName"),
                    @Mapping(target = "majorName", source = "majorId", qualifiedByName = "getMajorName")
            }
    )
    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm toForm(User entity);

    @InheritInverseConfiguration(name = "toForm")
    User toEntity(UserForm entity);

    @Mappings(
            {

                    @Mapping(target = "userId", source = "id"),
                    @Mapping(target = "role", source = "roleId", qualifiedByName = "getRoleCode"),
                    @Mapping(target = "genderLabel",expression =
                            "java(com.lee.selection.common.base.IBaseEnum.getLabelByValue(entity.getGender(), " +
                                    "com.lee.selection.system.enums.GenderEnum.class))"),
                    @Mapping(target = "clazzName", source = "clazzId", qualifiedByName = "getClazzName"),
                    @Mapping(target = "majorName", source = "majorId", qualifiedByName = "getMajorName")
            }
    )
    UserVO toVo(User entity);


    @Mappings(
            {
                    @Mapping(target = "role", source = "roleId", qualifiedByName = "getRoleCode")
            }
    )
    UserProfileVO toProfile(User entity);

    User toEntity(ProfileForm entity);

}
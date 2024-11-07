package com.lee.selection.system.converter;



import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lee.selection.system.model.entity.User;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户转换器
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Mapper(componentModel = "spring")
public interface UserConverter {




    Page<User> toPageVo(Page<User> bo);






    UserVO toVo(User entity);


    UserProfileVO toProfile(User entity);


}
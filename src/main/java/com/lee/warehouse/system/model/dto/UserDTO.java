package com.lee.warehouse.system.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.lee.warehouse.system.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户DTO
 */
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDTO  extends User{
    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setNickname(user.getNickname());
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        setRole(user.getRole());
        setCreateTime(user.getCreateTime());
        setUpdateTime(user.getUpdateTime());
        setAvatar(user.getAvatar());
        setStatus(user.getStatus());
    }
    public UserDTO() {
    }
}
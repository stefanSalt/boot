package com.lee.selection.system.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lee.selection.system.model.entity.Recruiter;
import com.lee.selection.system.model.entity.Student;
import com.lee.selection.system.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户DTO
 */
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDTO  extends User{
    private Student student;
    private Recruiter recruiter;

    public UserDTO(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setNickname(user.getNickname());
        this.setPassword(user.getPassword());
        this.setAvatar(user.getAvatar());
        this.setGender(user.getGender());
        this.setPhone(user.getPhone());
        this.setEmail(user.getEmail());
        this.setCreateTime(user.getCreateTime());
        this.setUpdateTime(user.getUpdateTime());
        this.setRoleId(user.getRoleId());
        this.setRole(user.getRole());
        this.setStatus(user.getStatus());
    }

    public UserDTO() {
    }
}
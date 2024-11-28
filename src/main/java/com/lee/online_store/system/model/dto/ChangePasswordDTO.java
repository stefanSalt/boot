package com.lee.online_store.system.model.dto;

import lombok.Data;

/**
 * TODO
 */
@Data
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private Integer id;
}
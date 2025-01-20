package com.lee.lecture.system.model.dto;

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
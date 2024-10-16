package com.lee.reservation.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 个人中心用户信息视图对象
 *
 */
@Data
@Schema(description = "个人中心表单对象")
public class ProfileForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")

    private Integer id;

    @Schema(description = "用户名")

    private String username;

    @Schema(description = "昵称")

    private String nickname;


    @Schema(description = "phone")

    private String phone;

    @Schema(description = "邮箱")

    private String email;

    @Schema(description = "性别")

    private Integer gender;

    @Schema(description = "头像url")

    private String avatar;



}
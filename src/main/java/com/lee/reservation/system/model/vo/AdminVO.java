package com.lee.reservation.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 管理员 VO
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Getter
@Setter
@Schema(description = "管理员视图对象")
public class AdminVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")

    private Integer userId;

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

    @Schema(description = "状态")

    private Integer status;

    @Schema(description = "备注")

    private String remark;

    private List<String> roles= List.of("admin");
}

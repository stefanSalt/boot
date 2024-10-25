package com.lee.selection.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 用户个人中心 视图对象
 */
@Getter
@Setter
@Schema(description = "用户个人中心 视图对象")
public class UserProfileVO {
    @Schema(description = "编号")
    private Integer id;

    @Schema(description = "昵称")
    private String name;

    @Schema(description = "角色名称")
    private String role;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "入校日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;


}
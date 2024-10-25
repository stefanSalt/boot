package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * 包含  管理员  教师  学生等 DTO
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Getter
@Setter
@Schema( description = "包含  管理员  教师  学生等传输层对象")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "学号/教师编号")

private String code;

        @Schema(description = "角色id")

private Integer roleId;

        @Schema(description = "姓名")

private String name;

        @Schema(description = "密码")

private String password;

        @Schema(description = "邮箱")

private String email;

        @Schema(description = "手机号")

private String phone;

        @Schema(description = "头像")

private String avatar;

        @Schema(description = "性别")

private Integer gender;

        @Schema(description = "出生年月")

private LocalDate birthday;

        @Schema(description = "入学日期")

private LocalDate entryDate;

        @Schema(description = "身份证号")

private String idCard;

        @Schema(description = "专业班级id")

private Integer clazzId;

        @Schema(description = "所在专业id")

private Integer majorId;

        @Schema(description = "备注")

private String remark;
}

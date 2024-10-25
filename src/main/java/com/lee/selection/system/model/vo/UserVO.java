package com.lee.selection.system.model.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lee.selection.system.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 VO
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Getter
@Setter
@Schema( description = "用户视图对象")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

    private Integer userId;

        @Schema(description = "学号/教师编号")

    private String code;

        @Schema(description = "角色名称")

    private String  role;

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

    private String genderLabel;

        @Schema(description = "出生年月")

    private LocalDate birthday;

        @Schema(description = "入学日期")

    private LocalDate entryDate;

        @Schema(description = "身份证号")

    private String idCard;

        @Schema(description = "班级")

    private String clazzName;

        @Schema(description = "所在专业")

    private String majorName;

        @Schema(description = "备注")

    private String remark;
}

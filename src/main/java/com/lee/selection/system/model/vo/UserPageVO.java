package com.lee.selection.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 分页VO
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Getter
@Setter
@Schema(description = "用户分页视图对象")
public class UserPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "编号")

    private Integer id;

    @Schema(description = "学号/教师编号")

    private String code;

    @Schema(description = "角色id")

    private Integer roleId;

    @Schema(description = "姓名")

    private String name;


    @Schema(description = "邮箱")

    private String email;

    @Schema(description = "手机号")

    private String phone;

    @Schema(description = "头像")

    private String avatar;

    @Schema(description = "性别")

    private String genderLabel;

    @Schema(description = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "入学日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    @Schema(description = "身份证号")

    private String idCard;

    @Schema(description = "班级名")

    private String clazzName;

    @Schema(description = "所在专业")

    private String majorName;

    @Schema(description = "备注")

    private String remark;

    @Schema(description = "状态")

    private Integer status;
}

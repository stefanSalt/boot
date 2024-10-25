package com.lee.selection.system.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 包含  管理员  教师  学生等
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Getter
@Setter
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号/教师编号
     */
    private String code;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生年月
     */
    private LocalDate birthday;

    /**
     * 入学日期
     */
    private LocalDate entryDate;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 专业班级id
     */
    private Integer clazzId;

    /**
     * 所在专业id
     */
    private Integer majorId;

    /**
     * 备注
     */
    private String remark;
}

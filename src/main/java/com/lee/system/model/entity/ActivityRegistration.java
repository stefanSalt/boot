package com.lee.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动报名表实体
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Getter
@Setter
@TableName("activity_registration")
public class ActivityRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Activity activity;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 状态：0-已报名 1-已签到 2-已取消
     */
    private Integer status;

    /**
     * 评分
     */
    private Integer score;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

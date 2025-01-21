package com.lee.questionnaire.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 问卷表实体
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Getter
@Setter
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 问卷说明
     */
    private String description;

    /**
     * 创建者ID
     */
    private Integer creatorId;

    /**
     * 状态(DRAFT/PUBLISHED/CLOSED)
     */
    private String status;

    /**
     * 问卷访问码
     */
    private String accessCode;

    /**
     * 问卷访问链接
     */
    private String accessUrl;

    /**
     * 访问密码
     */
    private String password;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否匿名问卷
     */
    private Byte isAnonymous;

    /**
     * 最大答卷数
     */
    private Integer maxResponse;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Byte deleted;

    @TableField(exist = false)
    private List<Question> questions;
}

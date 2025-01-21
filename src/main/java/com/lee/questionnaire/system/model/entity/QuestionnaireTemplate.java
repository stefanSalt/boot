package com.lee.questionnaire.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 问卷模板表实体
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("questionnaire_template")
public class QuestionnaireTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板标题
     */
    private String title;

    /**
     * 模板说明
     */
    private String description;

    /**
     * 创建者ID
     */
    private Integer creatorId;

    /**
     * 模板分类
     */
    private String category;

    /**
     * 封面图片URL
     */
    private String coverImage;

    /**
     * 问题和选项内容(JSON)
     */
    private String questionsJson;

    /**
     * 是否公开模板
     */
    private Byte isPublic;

    /**
     * 使用次数
     */
    private Integer useCount;

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
}

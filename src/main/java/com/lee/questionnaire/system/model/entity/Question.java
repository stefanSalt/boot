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
 * 问题表实体
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Getter
@Setter
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问卷ID
     */
    private Integer questionnaireId;

    /**
     * 题目内容
     */
    private String title;

    /**
     * 题目类型(SINGLE/MULTIPLE/TEXT/SCORE)
     */
    private String type;

    /**
     * 是否必答
     */
    private Byte required;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 评分最大值
     */
    private Integer scoreMax;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<QuestionOption> options;
}

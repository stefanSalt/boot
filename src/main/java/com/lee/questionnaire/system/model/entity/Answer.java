package com.lee.questionnaire.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 答案表实体
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Getter
@Setter
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 答卷ID
     */
    private Integer responseId;

    /**
     * 问题ID
     */
    private Integer questionId;

    /**
     * 选项ID(单选题)
     */
    private Integer optionId;

    /**
     * 选项ID字符串(多选题)
     */
    private String optionIds;

    /**
     * 文本答案
     */
    private String textAnswer;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private Question question;
}

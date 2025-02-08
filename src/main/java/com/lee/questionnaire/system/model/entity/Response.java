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
 * 答卷表实体
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Getter
@Setter
public class Response implements Serializable {

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
     * 答卷人ID
     */
    private Integer userId;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 答卷表关联的答案表
     */
    @TableField(exist = false)
    private List<Answer> answers;

    @TableField(exist = false)
    private Questionnaire questionnaire;
}

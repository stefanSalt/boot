package com.lee.lecture.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 讲座评价表实体
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Getter
@Setter
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评价ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 讲座ID
     */
    private Integer lectureId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 评分(1-5分)
     */
    private Byte rating;

    /**
     * 评价内容
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Lecture lecture;

    @TableField(exist = false)
    private User user;
}

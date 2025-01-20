package com.lee.lecture.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 讲座预约表实体
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Getter
@Setter
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预约ID
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
     * 状态(1-已预约/0-已取消)
     */
    private Byte status;

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

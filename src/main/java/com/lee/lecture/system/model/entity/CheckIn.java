package com.lee.lecture.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 讲座签到表实体
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("check_in")
public class CheckIn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 签到ID
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
     * 签到时间
     */
    private LocalDateTime checkInTime;

    /**
     * 签到类型(1-/2-手动)
     */
    private Byte checkInType;



    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Lecture lecture;
}

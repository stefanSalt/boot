package com.lee.reservation.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程信息实体
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 课程类型
     */
    private Integer type;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 上课地点
     */
    private String location;

    /**
     * 0下架1上架2结束
     */
    private Integer status;

    /** 课时数 */
    private Integer durationHours;
}

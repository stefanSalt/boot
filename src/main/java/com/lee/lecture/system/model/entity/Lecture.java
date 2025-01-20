package com.lee.lecture.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 讲座信息表实体
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Getter
@Setter
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲座ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 讲座标题
     */
    private String title;

    /**
     * 讲座描述
     */
    private String description;

    /**
     * 讲师ID
     */
    private Integer lecturerId;

    /**
     * 地点
     */
    private String location;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 状态(0-未开始/1-进行中/2-已结束)
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

    private String coverUrl;
}

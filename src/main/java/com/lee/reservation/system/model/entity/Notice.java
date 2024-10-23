package com.lee.reservation.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告信息实体
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Getter
@Setter
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 发布人
     */
    private Integer publisher;

    /**
     * 类型
     */
    private Integer type;

    /** 状态0未发布1已发布 */
    private Integer status;
}

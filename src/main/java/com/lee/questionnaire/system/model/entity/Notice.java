package com.lee.questionnaire.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 公告表实体
 *
 * @author baomidou
 * @since 2025-01-20
 */
@Getter
@Setter
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告类型(NORMAL/IMPORTANT/URGENT)
     */
    private String type;

    /**
     * 发布人ID
     */
    private Integer publisherId;

    /**
     * 状态(DRAFT/PUBLISHED/EXPIRED)
     */
    private String status;

    /**
     * 是否置顶
     */
    private Byte isTop;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

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

    private String publisherName;
}

package com.lee.lecture.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 资讯信息表实体
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Getter
@Setter
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资讯ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资讯标题
     */
    private String title;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 封面图片URL
     */
    private String coverUrl;

    /**
     * 作者ID
     */
    private Integer authorId;

    /**
     * 状态(0-草稿/1-已发布/2-已下线)
     */
    private Byte status;

    /**
     * 是否置顶(0-否/1-是)
     */
    private Byte isTop;

    /**
     * 阅读量
     */
    private Integer viewCount;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String categoryName;
}

package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论实体
 *
 * @author baomidou
 * @since 2024-12-05
 */
@Getter
@Setter
@TableName("t_review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer productId;


    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String authorName;

    private String authorAvatar;

    @TableField(exist = false)
    private List<Review> children;
}

package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品信息实体
 *
 * @author baomidou
 * @since 2024-11-27
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("t_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 分类id
     */
    private Integer categoryId;

    @TableField(exist = false)
    private String categoryName;

    /**
     * 价格
     */
    private Long price;

    /**
     * 描述
     */
    private String description;

    /**
     * 库存
     */
    private Integer stockQuantity;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 图像1url
     */
    private String img1;

    /**
     * 图像2url
     */
    private String img2;

    /**
     * 图像3url
     */
    private String img3;

    /**
     * 图像4url
     */
    private String img4;

    private Integer status;

    @TableField(exist = false)
    private List<String> imgs;
}

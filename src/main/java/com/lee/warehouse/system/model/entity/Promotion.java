package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 促销活动实体
 *
 * @author baomidou
 * @since 2024-11-27
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("t_promotion")
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动名称
     */
    private String promotionName;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 优惠类型(满减/折扣)
     */
    private Integer discountType;

    /**
     * 优惠值（如折扣率、减免金额等）
     */
    private BigDecimal discountValue;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<Product> products;

    @TableField(exist = false)
    private List<Integer> productIds;
}

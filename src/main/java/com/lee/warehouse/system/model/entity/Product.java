package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 产品信息实体
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Getter
@Setter
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 类别
     */
    private String category;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 单位
     */
    private String unit;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;
}

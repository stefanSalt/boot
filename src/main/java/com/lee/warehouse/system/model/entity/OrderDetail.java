package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单详情信息实体
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Getter
@Setter
@TableName("order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单详情ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 订单中产品名称
     */
    private String productName;

    /**
     * 订单中产品数量
     */
    private Integer quantity;

    /**
     * 订单中产品单价
     */
    private BigDecimal price;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;
}

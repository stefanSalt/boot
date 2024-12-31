package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单信息实体
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Getter
@Setter
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 订单日期
     */
    private LocalDate orderDate;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 送货地址
     */
    private String shippingAddress;

    /**
     * 操作人
     */
    private Integer operator;

    /**
     * 进出库类型
     */
    private String transactionType;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private Suppliers supplier;

    @TableField(exist = false)
    private List<OrderDetail> orderItems;

    @TableField(exist = false)
    private LocalDate startDate;

    @TableField(exist = false)
    private LocalDate endDate;
}

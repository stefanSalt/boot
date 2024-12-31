package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 库存交易信息实体
 *
 * @author baomidou
 * @since 2024-12-26
 */
@Getter
@Setter
@TableName("inventory_transaction")
public class InventoryTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 交易日期
     */
    private LocalDate transactionDate;

    /**
     * 交易订单id
     */
    private Integer orderId;

    /**
     * 交易类型
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
    private String supplierName;
    @TableField(exist = false)
    private String customerName;

    @TableField(exist = false)
    private Order order;

    @TableField(exist = false)
    private LocalDate startDate;

    @TableField(exist = false)
    private LocalDate endDate;

}

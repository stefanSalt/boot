package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lee.online_store.system.model.vo.OrderProductVO;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单实体
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Getter
@Setter
@TableName("t_order")
public class Order implements Serializable {

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
     * (0待支付1已支付2已完成)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 总价
     */
    private BigDecimal totalAmount;

    /**
     * 优惠总价
     */
    private BigDecimal discountAmount;

    @TableField(exist = false)
    private List<OrderProductVO> orderProducts;

    @TableField(exist = false)
    private String paymentMethod;
}

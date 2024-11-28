package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单商品关联实体
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Getter
@Setter
@TableName("t_order_product")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private Long price;

    /**
     * 折扣
     */
    private Long discountValue;

    /**
     * 折扣后金额
     */
    private Long discountAmount;
}

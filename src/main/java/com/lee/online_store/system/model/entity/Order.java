package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 总价
     */
    private Long totalAmount;

    /**
     * 优惠总价
     */
    private Long discountAmount;

    @TableField(exist = false)
    private List<OrderProductVO> orderProducts;
}

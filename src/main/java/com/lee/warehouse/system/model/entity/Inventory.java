package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 库存信息实体
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Getter
@Setter
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 库存数量
     */
    private Integer quantity;

    /**
     * 生产日期
     */
    private LocalDate productionDate;

    /**
     * 有效期
     */
    private LocalDate expirationDate;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Warehouse warehouse;

    @TableField(exist = false)
    private Product product;
}

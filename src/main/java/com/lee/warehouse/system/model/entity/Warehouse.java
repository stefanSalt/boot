package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 仓库信息实体
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Getter
@Setter
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员id
     */
    private Integer manager;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库位置
     */
    private String location;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 性质
     */
    private String type;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;
}

package com.lee.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务表实体
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Getter
@Setter
@TableName("service_info")
public class ServiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务图片
     */
    private String image;

    /**
     * 服务介绍
     */
    private String description;

    /**
     * 状态：0-下架 1-上架
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

}

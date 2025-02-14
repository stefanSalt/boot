package com.lee.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 预约记录表实体
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Getter
@Setter
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;
    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private ServiceInfo service;
    /**
     * 服务ID
     */
    private Integer serviceId;

    /**
     * 状态：0-待确认 1-已确认 2-已完成 3-已取消
     */
    private Integer status;


    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
@TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

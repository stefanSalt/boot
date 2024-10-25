package com.lee.selection.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 车辆信息实体
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Getter
@Setter
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌
     */
    private String plate;

    /**
     * 颜色
     */
    private String color;

    /**
     * 车辆类型
     */
    private Integer type;

    /**
     * 车型
     */
    private String brandModel;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 教练id
     */
    private Integer coachId;
}

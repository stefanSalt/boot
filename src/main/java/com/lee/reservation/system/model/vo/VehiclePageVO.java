package com.lee.reservation.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 车辆信息 分页VO
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Getter
@Setter
@Schema( description = "车辆信息分页视图对象")
public class VehiclePageVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

    private Integer id;

        @Schema(description = "车牌")

    private String plate;

        @Schema(description = "颜色")

    private String color;

        @Schema(description = "车辆类型")

    private Integer type;

        @Schema(description = "车型")

    private String brandModel;

        @Schema(description = "状态")

    private Integer status;

        @Schema(description = "备注")

    private String remarks;

        @Schema(description = "教练id")

    private Integer coachId;
}

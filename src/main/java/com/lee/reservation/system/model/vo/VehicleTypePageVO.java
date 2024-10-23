package com.lee.reservation.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 车辆类型 分页VO
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Getter
@Setter
@Schema( description = "车辆类型分页视图对象")
public class VehicleTypePageVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

    private Integer id;

        @Schema(description = "名称")

    private String name;

        @Schema(description = "remark")

    private String remark;
}

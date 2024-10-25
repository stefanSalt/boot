package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * 车辆类型 DTO
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Getter
@Setter
@Schema( description = "车辆类型传输层对象")
public class VehicleTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "名称")

private String name;

        @Schema(description = "remark")

private String remark;
}

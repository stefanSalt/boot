package com.lee.reservation.system.model.query;

import com.lee.reservation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 车辆信息分页查询对象
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Schema(description ="车辆信息分页查询对象")
@Data
public class VehiclePageQuery extends BasePageQuery {

    @Schema(description="车牌号")
    private String plate;
    @Schema(description="颜色")
    private String color;
    @Schema(description="教练id")
    private Integer coachId;
    @Schema(description="状态")
    private Integer status;
    @Schema(description = "车型")
    private String brandModel;

}

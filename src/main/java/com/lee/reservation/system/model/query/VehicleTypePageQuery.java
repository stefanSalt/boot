package com.lee.reservation.system.model.query;

import com.lee.reservation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 车辆类型分页查询对象
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Schema(description ="车辆类型分页查询对象")
@Data
public class VehicleTypePageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

}

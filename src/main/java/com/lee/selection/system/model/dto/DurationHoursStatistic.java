package com.lee.selection.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 课时时数统计
 *
 */
@Data
@Schema(description = "课时时数统计")
public class DurationHoursStatistic {
    @Schema(description = "课程类型")
    private String name;

    @Schema(description = "课时数")
    private Integer num;

}
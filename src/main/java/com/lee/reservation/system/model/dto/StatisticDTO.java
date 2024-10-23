package com.lee.reservation.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 统计数据
 *
 * @Date 2024-10-22 16:15
 */
@Schema(description = "统计数据")
@Data
public class StatisticDTO {

    private List<String> names;

    private List<Integer> nums;

}
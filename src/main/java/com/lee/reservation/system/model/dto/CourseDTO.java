package com.lee.reservation.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 课程信息 DTO
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
@Schema( description = "课程信息传输层对象")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "课程名称")

private String name;

        @Schema(description = "课程描述")

private String description;

        @Schema(description = "课程类型")

private Integer type;

        @Schema(description = "开始时间")

private LocalDateTime startTime;

        @Schema(description = "结束时间")

private LocalDateTime endTime;

        @Schema(description = "上课地点")

private String location;

        @Schema(description = "0下架1上架2结束")

private Integer status;


private Integer durationHours;
}

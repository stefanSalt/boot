package com.lee.selection.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程信息 分页VO
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
@Schema(description = "课程信息分页视图对象")
public class CoursePageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "课程描述")
    private String description;

    @Schema(description = "课程类型")
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(description = "上课地点")

    private String location;

    @Schema(description = "0下架1上架2结束")
    private Integer status;

    @Schema(description = "课程时长(小时")
    private Integer durationHours;
}

package com.lee.selection.system.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 课程信息分页查询对象
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Schema(description ="课程信息分页查询对象")
@Data
public class CoursePageQuery extends BasePageQuery {

    @Schema(description="名称")
    private String name;

    @Schema(description="状态")
    private Integer status;

    @Schema(description="开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @Schema(description="结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;


    @Schema(description="课程类型")
    private Integer type;

}

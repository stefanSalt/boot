package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 课程信息表 DTO
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
@Schema( description = "课程信息表传输层对象")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "课程编号")

private String code;

        @Schema(description = "课程名称")

private String name;

        @Schema(description = "学分")

private Integer credit;

        @Schema(description = "学时")

private Integer duration;

        @Schema(description = "所属专业")

private Integer majorId;

        @Schema(description = "考试分占中成绩的比值")

private Integer examScoreRatio;

        @Schema(description = "类型")

private Integer type;

        @Schema(description = "课程属性（必修  选修）")

private Integer attribute;

        @Schema(description = "开课时间")

private LocalDateTime startTime;

        @Schema(description = "开课状态")

private Integer status;
}

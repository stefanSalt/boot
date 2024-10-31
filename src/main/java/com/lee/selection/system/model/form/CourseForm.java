package com.lee.selection.system.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 课程信息表 表单对象
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
@Schema(description = "课程信息表表单对象")
public class CourseForm implements Serializable {

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
        @JsonFormat(pattern = "yyyy-MM-dd")
private LocalDate startTime;

        @Schema(description = "开课状态")

private Integer status;


        private List<Integer> teacherIds;
}

package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 申请表 DTO
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
@Schema( description = "申请表传输层对象")
public class ApplyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "学生id")

private Integer studentId;

        @Schema(description = "教师id")

private Integer teacherId;

        @Schema(description = "课程id")

private Integer courseId;

        @Schema(description = "状态(0:学生申请，1：教师审核)")

private Integer status;

        @Schema(description = "学生申请内容")

private String content;

        @Schema(description = "教师意见")

private String reply;

        @Schema(description = "创建时间")

private LocalDateTime createtime;

        @Schema(description = "教师审批时间")

private LocalDateTime replyTime;

        @Schema(description = "备注")

private String remark;
}

package com.lee.selection.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请表 分页VO
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
@Schema(description = "申请表分页视图对象")
public class ApplyPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "编号")

    private Integer id;

    @Schema(description = "学生")

    private String studentName;

    @Schema(description = "教师")

    private String teacherName;

    @Schema(description = "课程")

    private String courseName;

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

package com.lee.selection.system.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 成绩表单对象
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Getter
@Setter
@Schema(description = "成绩表单对象")
public class GradeForm implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "学生id")

private Integer studentId;

        @Schema(description = "成绩")

private Integer grade;

        @Schema(description = "绩点")

private BigDecimal gradePoint;

        @Schema(description = "课程id")

private Integer courseId;

        @Schema(description = "备注")

private String remark;

        @Schema(description = "教师id")

private Integer teacherId;

    @Schema(description = "状态")
    private Integer status;
}

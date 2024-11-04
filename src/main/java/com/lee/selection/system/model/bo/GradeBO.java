package com.lee.selection.system.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 成绩
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Getter
@Setter
public class GradeBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 成绩
     */
    private Integer grade;

    /**
     * 绩点
     */
    private BigDecimal gradePoint;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 教师id
     */
    private Integer teacherId;

    @Schema(description = "状态")
    private Integer status;
}

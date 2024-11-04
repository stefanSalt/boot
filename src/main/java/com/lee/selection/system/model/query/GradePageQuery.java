package com.lee.selection.system.model.query;

import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 成绩分页查询对象
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Schema(description ="成绩分页查询对象")
@Data
public class GradePageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

    @Schema(description="学生id")
    private Integer studentId;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description="课程id")
    private Integer courseId;


    @Schema(description="教师id")
    private Integer teacherId;

}

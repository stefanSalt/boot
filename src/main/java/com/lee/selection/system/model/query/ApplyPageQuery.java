package com.lee.selection.system.model.query;

import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 申请表分页查询对象
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Schema(description ="申请表分页查询对象")
@Data
public class ApplyPageQuery extends BasePageQuery {



    @Schema(description="状态")
    private Integer status;

    @Schema(description="学号")
    private Integer studentId;

    @Schema(description="课程id")
    private Integer courseId;

    @Schema(description="教师id")
    private Integer teacherId;



}

package com.lee.reservation.system.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.reservation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 预约记录分页查询对象
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Schema(description ="预约记录分页查询对象")
@Data
public class ReservationPageQuery extends BasePageQuery {

    @Schema(description="教练id")
    private Integer instructorId;
    @Schema(description="学生id")
    private Integer studentId;
    @Schema(description="课程id")
    private Integer courseId;
    @Schema(description="预约状态")
    private Integer status;
    @Schema(description="开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

}

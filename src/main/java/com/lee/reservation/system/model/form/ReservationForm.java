package com.lee.reservation.system.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 预约记录 表单对象
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
@Schema(description = "预约记录表单对象")
public class ReservationForm implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "教练id")

private Integer instructorId;

        @Schema(description = "课程id")

private Integer courseId;

        @Schema(description = "学员id")

private Integer studentId;

        @Schema(description = "0待审核1审核通过2审核未通过3取消")

private Integer status;

        @Schema(description = "预约时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime reservationTime;

        @Schema(description = "开始时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime startTime;

        @Schema(description = "备注")

private String remark;

        @Schema(description = "课时数")

private Integer durationHours;
}

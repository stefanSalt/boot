package com.lee.reservation.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 预约记录 DTO
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
@Schema( description = "预约记录传输层对象")
public class ReservationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "教练id")

private Integer instructorId;

        @Schema(description = "课程id")

private Integer courseId;

        @Schema(description = "学员id")

private Integer userId;

        @Schema(description = "0待审核1审核通过2审核未通过3取消")

private Integer reservationStatus;

        @Schema(description = "预约时间")

private LocalDateTime reservationTime;

        @Schema(description = "开始时间")

private LocalDateTime startTime;

        @Schema(description = "备注")

private String remark;

        @Schema(description = "课时数")

private Integer durationHours;
}

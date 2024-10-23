package com.lee.reservation.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 预约记录 分页VO
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
@Schema(description = "预约记录分页视图对象")
public class ReservationPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")

    private Integer id;

    @Schema(description = "教练名")
    private String instructorName;

    @Schema(description = "课程名")
    private String courseName;

    @Schema(description = "学员名")
    private String studentName;


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

package com.lee.reservation.system.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 *  学车日历查询参数
 *
 * @Date 2024-10-21 15:34
 */
@Getter
@Setter
@Schema(description = "预约日历查询参数")
public class ReservationCalendarQuery {
    @Schema(description = "学员ID")
    private Long studentId;

   @Schema(description = "日期")
   @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate selectedDate;
}
package com.lee.lecture.system.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 预约人员名单导出VO
 */
@Data
public class LectureReservationExcelVO {
    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("预约时间")
    private String reserveTime;

    @ExcelProperty("签到状态")
    private String checkInStatus;

    @ExcelProperty("签到时间")
    private String checkInTime;

}
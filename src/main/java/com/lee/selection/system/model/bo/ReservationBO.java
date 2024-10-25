package com.lee.selection.system.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 预约记录
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Getter
@Setter
public class ReservationBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教练id
     */
    private Integer instructorId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 学员id
     */
    private Integer studentId;

    /**
     * 0待审核1审核通过2审核未通过3取消
     */
    private Integer status;

    /**
     * 预约时间
     */
    private LocalDateTime reservationTime;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 课时数
     */
    private Integer durationHours;
}

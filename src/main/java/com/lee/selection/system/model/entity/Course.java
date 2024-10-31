package com.lee.selection.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 课程信息表实体
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程编号
     */
    private String code;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 学时
     */
    private Integer duration;

    /**
     * 所属专业
     */
    private Integer majorId;

    /**
     * 考试分占中成绩的比值
     */
    private Integer examScoreRatio;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 课程属性（必修  选修）
     */
    private Integer attribute;

    /**
     * 开课时间
     */
    private LocalDate startTime;

    /**
     * 开课状态
     */
    private Integer status;
}

package com.lee.selection.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Getter
@Setter
@TableName("job_info")
public class JobInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 职责
     */
    private String responsibility;

    /**
     * 要求
     */
    private String requirement;

    /**
     * 地址
     */
    private String workAddress;

    /**
     * 工作时间
     */
    private String workTime;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 状态
     */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 兼职全职
     */
    private Integer type;

    private Integer recruiterId;
}

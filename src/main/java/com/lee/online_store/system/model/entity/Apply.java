package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.online_store.system.model.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请实体
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Getter
@Setter
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer stuId;

    private Long jobId;

    private String resume;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 查看状态
     */
    private Integer status;

    /**
     * 招聘方id
     */
    private Integer recruiterId;

    @TableField(exist = false)
    private UserDTO student;

    @TableField(exist = false)
    private UserDTO recruiter;

    @TableField(exist = false)
    private JobInfo jobInfo;
}

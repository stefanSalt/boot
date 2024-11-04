package com.lee.selection.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请表实体
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Getter
@Setter
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 状态(0:学生申请，1：教师审核)
     */
    private Integer status;

    /**
     * 学生申请内容
     */
    private String content;

    /**
     * 教师意见
     */
    private String reply;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 教师审批时间
     */
    private LocalDateTime replyTime;

    /**
     * 备注
     */
    private String remark;
}

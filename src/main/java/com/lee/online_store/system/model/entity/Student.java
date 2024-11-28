package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 学生信息实体
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Getter
@Setter
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private Integer grade;
}

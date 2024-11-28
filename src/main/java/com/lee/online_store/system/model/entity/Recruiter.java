package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 招聘者实体
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Getter
@Setter
public class Recruiter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 企业id
     */
    private Integer companyId;

    /**
     * 部门
     */
    private String department;

    /**
     * 职位
     */
    private String position;


}

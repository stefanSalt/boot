package com.lee.online_store.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 公告信息实体
 *
 * @author baomidou
 * @since 2024-11-14
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("t_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详情
     */
    private String content;


    private Integer status;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;


}

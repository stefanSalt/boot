package com.lee.selection.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 选课设置实体
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Getter
@Setter
@TableName("course_selection_setting")
public class CourseSelectionSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开始选课时间
     */
    private LocalDateTime startTime;

    /**
     * 结束选课时间
     */
    private LocalDateTime endTime;
}

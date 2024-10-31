package com.lee.selection.system.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 班级
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Getter
@Setter
public class ClazzBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属专业id
     */
    private Integer parentId;

    /**
     * 班级描述
     */
    private String description;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班主任
     */
    private Integer teacherId;
}

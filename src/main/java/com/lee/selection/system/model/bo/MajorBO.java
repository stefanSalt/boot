package com.lee.selection.system.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 专业信息表
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Getter
@Setter
public class MajorBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属上级节点
     */
    private Integer parentId;

    /**
     * 备注
     */
    private String remark;
}

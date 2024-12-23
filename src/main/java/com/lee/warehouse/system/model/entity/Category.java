package com.lee.warehouse.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品分类实体
 *
 * @author baomidou
 * @since 2024-11-26
 */
@Getter
@Setter
@TableName("t_category")
public class Category  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<Category> children;
}

package com.lee.selection.system.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色信息表 表单对象
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Getter
@Setter
@Schema(description = "角色信息表表单对象")
public class RoleForm implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "角色名称")

private String name;

        @Schema(description = "备注")

private String remark;

        @Schema(description = "状态码")

private String code;
}

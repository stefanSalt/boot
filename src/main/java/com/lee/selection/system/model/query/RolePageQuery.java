package com.lee.selection.system.model.query;

import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色信息表分页查询对象
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Schema(description ="角色信息表分页查询对象")
@Data
public class RolePageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

}

package com.lee.reservation.system.model.query;

import com.lee.reservation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理员分页查询对象
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Schema(description ="管理员分页查询对象")
@Data
public class AdminPageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

    @Schema(description="状态")
    private Integer status;

}

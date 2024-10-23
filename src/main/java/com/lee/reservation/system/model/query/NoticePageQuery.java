package com.lee.reservation.system.model.query;

import com.lee.reservation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 公告信息分页查询对象
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Schema(description ="公告信息分页查询对象")
@Data
public class NoticePageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

    @Schema(description = "公告类型")
    private Integer type;

    @Schema(description = "公告状态")
    private Integer status;

}

package com.lee.selection.system.model.query;

import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 班级分页查询对象
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Schema(description ="班级分页查询对象")
@Data
public class ClazzPageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

}

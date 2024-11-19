package com.lee.selection.system.model.query;

import com.lee.selection.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 选课设置分页查询对象
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Schema(description ="选课设置分页查询对象")
@Data
public class CourseSelectionSettingPageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

}

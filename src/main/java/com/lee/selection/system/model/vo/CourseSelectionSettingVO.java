package com.lee.selection.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 选课设置 VO
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Getter
@Setter
@Schema( description = "选课设置视图对象")
public class CourseSelectionSettingVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

    private Integer id;

        @Schema(description = "开始选课时间")

    private LocalDateTime startTime;

        @Schema(description = "结束选课时间")

    private LocalDateTime endTime;
}

package com.lee.selection.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 班级 VO
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Getter
@Setter
@Schema( description = "班级视图对象")
public class ClazzVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

    private Integer id;

        @Schema(description = "所属专业id")

    private Integer parentId;

        @Schema(description = "班级描述")

    private String description;

        @Schema(description = "班级名称")

    private String name;

        @Schema(description = "班主任")

    private Integer teacherId;
}

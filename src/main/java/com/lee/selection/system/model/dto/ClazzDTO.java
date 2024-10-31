package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 班级 DTO
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Getter
@Setter
@Schema( description = "班级传输层对象")
public class ClazzDTO implements Serializable {

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

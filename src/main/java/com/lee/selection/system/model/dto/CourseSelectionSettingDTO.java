package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 选课设置 DTO
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Getter
@Setter
@Schema( description = "选课设置传输层对象")
public class CourseSelectionSettingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "开始选课时间")

private LocalDateTime startTime;

        @Schema(description = "结束选课时间")

private LocalDateTime endTime;
}

package com.lee.selection.system.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 选课设置 表单对象
 *
 * @author baomidou
 * @since 2024-11-04
 */
@Getter
@Setter
@Schema(description = "选课设置表单对象")
public class CourseSelectionSettingForm implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "编号")

private Integer id;

        @Schema(description = "开始选课时间")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime startTime;

        @Schema(description = "结束选课时间")

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime endTime;
}

package com.lee.reservation.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告信息 表单对象
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Getter
@Setter
@Schema(description = "公告信息表单对象")
public class NoticeForm implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "标题")

private String title;

        @Schema(description = "内容")

private String content;

        @Schema(description = "发布时间")

private LocalDateTime publishTime;

        @Schema(description = "发布人")

private Integer publisher;

        @Schema(description = "类型")

private Integer type;

        @Schema(description = "状态")

private Integer status;
}

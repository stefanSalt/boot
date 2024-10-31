package com.lee.selection.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 专业信息表 DTO
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Getter
@Setter
@Schema( description = "专业信息表传输层对象")
public class MajorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "id")

private Integer id;

        @Schema(description = "编码")

private String code;

        @Schema(description = "名称")

private String name;

        @Schema(description = "所属上级节点")

private Integer parentId;

        @Schema(description = "备注")

private String remark;
}

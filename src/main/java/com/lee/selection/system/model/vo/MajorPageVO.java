package com.lee.selection.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 专业信息表 分页VO
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Getter
@Setter
@Schema( description = "专业信息表分页视图对象")
public class MajorPageVO implements Serializable {

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

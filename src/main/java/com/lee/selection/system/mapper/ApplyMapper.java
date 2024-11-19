package com.lee.selection.system.mapper;

import com.lee.selection.system.model.dto.ApplyStatisticDTO;
import com.lee.selection.system.model.dto.StatisticDTO;
import com.lee.selection.system.model.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.ApplyBO;
import com.lee.selection.system.model.query.ApplyPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 申请表 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-31
 */

@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<ApplyBO> listPagedApplys(Page<ApplyBO> page, ApplyPageQuery queryParams);

    List<ApplyStatisticDTO> getCountsByCourse();

}

package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.Apply;
import com.lee.selection.system.model.vo.ApplyVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申请 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-13
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
    Page<ApplyVO> listPagedApplys(Page<Apply> page, ApplyVO queryParams);

}

package com.lee.selection.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.VehicleTypeBO;
import com.lee.selection.system.model.entity.VehicleType;
import com.lee.selection.system.model.query.VehicleTypePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆类型 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-22
 */

@Mapper
public interface VehicleTypeMapper extends BaseMapper<VehicleType> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<VehicleTypeBO> listPagedVehicleTypes(Page<VehicleTypeBO> page, VehicleTypePageQuery queryParams);

}

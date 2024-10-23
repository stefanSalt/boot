package com.lee.reservation.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.reservation.system.model.bo.VehicleBO;
import com.lee.reservation.system.model.entity.Vehicle;
import com.lee.reservation.system.model.query.VehiclePageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 车辆信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-16
 */

@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<VehicleBO> listPagedVehicles(Page<VehicleBO> page, @Param(value = "queryParams") VehiclePageQuery queryParams);

}

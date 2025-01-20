package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.Reservation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 讲座预约表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-07
 */

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Reservation> listPagedReservations(Page<Reservation> page, Reservation queryParams);

}

package com.lee.selection.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.ReservationBO;
import com.lee.selection.system.model.dto.DurationHoursStatistic;
import com.lee.selection.system.model.entity.Reservation;
import com.lee.selection.system.model.query.ReservationPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预约记录 Mapper 接口
 *
 * @author baomidou
 * @since 2024-10-17
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
    Page<ReservationBO> listPagedReservations(Page<ReservationBO> page,@Param("queryParams") ReservationPageQuery queryParams);

    List<DurationHoursStatistic> getDurationHoursByCourse();
}

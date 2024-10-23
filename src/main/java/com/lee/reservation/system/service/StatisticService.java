package com.lee.reservation.system.service;

import com.lee.reservation.system.model.dto.StatisticDTO;

/**
 * 统计服务
 *
 * @Date 2024-10-22 14:47
 */
public interface StatisticService {
    StatisticDTO getDurationHoursByCourse();

}

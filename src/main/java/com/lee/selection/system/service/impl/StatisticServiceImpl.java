package com.lee.selection.system.service.impl;

import com.lee.selection.system.model.dto.DurationHoursStatistic;
import com.lee.selection.system.model.dto.StatisticDTO;
import com.lee.selection.system.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统计服务实现类
 *
 */
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
//    private final ReservationMapper ReservationMapper;
//    private final StudentService studentService;

//    public Long getTotalReservationCount() {
//        return reservationService.count();
//    }

    @Override
    public StatisticDTO getDurationHoursByCourse() {
//        List<DurationHoursStatistic> durationHoursByCourse = ReservationMapper.getDurationHoursByCourse();
//        StatisticDTO statisticDTO = new StatisticDTO();
//        statisticDTO.setNames(durationHoursByCourse.stream().map(DurationHoursStatistic::getName).toList());
//        statisticDTO.setNums(durationHoursByCourse.stream().map(DurationHoursStatistic::getNum).toList());
//        return statisticDTO;
        return null;
    }


}
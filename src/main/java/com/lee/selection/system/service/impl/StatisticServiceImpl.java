package com.lee.selection.system.service.impl;

import com.lee.selection.system.mapper.ApplyMapper;
import com.lee.selection.system.model.dto.ApplyStatisticDTO;
import com.lee.selection.system.model.dto.StatisticDTO;
import com.lee.selection.system.service.ApplyService;
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

    private final ApplyService applyService;

    private final ApplyMapper applyMapper;

    @Override
    public StatisticDTO getApplysByCourse() {
        List<ApplyStatisticDTO> countsByCourse = applyMapper.getCountsByCourse();
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setNames(countsByCourse.stream().map(ApplyStatisticDTO::getName).toList());
        statisticDTO.setNums(countsByCourse.stream().map(ApplyStatisticDTO::getNum).toList());
        return statisticDTO;
    }


}
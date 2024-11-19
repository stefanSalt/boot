package com.lee.selection.system.controller;

import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.dto.StatisticDTO;
import com.lee.selection.system.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计数据接口
 *
 */
@Tag(name = "统计数据接口")
@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    /**
     * 学员个人课时时数统计
     * @return 课时时数统计
     */
    @Operation(summary = "学员个人课时时数统计")
    @GetMapping("/durationHoursByCourse")
    public Result getDurationHoursByCourse() {
        StatisticDTO durationHoursByCourse = statisticService.getApplysByCourse();
        return Result.success(durationHoursByCourse);
    }

//    @Operation(summary = "学员数统计")
//    @GetMapping("/studentsPerMonth")
//    public Result getDurationHours() {
//        return Result.success(statisticService.getStudentByMonth());
//    }
}
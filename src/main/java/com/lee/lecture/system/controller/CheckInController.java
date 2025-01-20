package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.lecture.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.model.entity.CheckIn;
import com.lee.lecture.system.service.CheckInService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 讲座签到表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Tag(name = "讲座签到表接口")
@RestController
@RequestMapping("/api/v1/checkins")
@RequiredArgsConstructor
public class CheckInController {

        private final CheckInService checkInService;

        @Operation(summary = "讲座签到表 分页列表")
        @GetMapping("/page")
        public Result listPagedCheckIns(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        CheckIn queryParams ) {
            IPage<CheckIn> result = checkInService.listPagedCheckIns(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增讲座签到表")
        @PostMapping
        public Result saveCheckIn(@RequestBody @Valid CheckIn formData ) {
            boolean result = checkInService.saveCheckIn(formData);
            return Result.judge(result);
        }

        @Operation(summary = "讲座签到表表单数据")
        @GetMapping("/{id}/form")
        public Result<CheckIn> getCheckIn(
            @Parameter(description = "讲座签到表ID") @PathVariable Long id
        ) {
            CheckIn formData = checkInService.getCheckInData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改讲座签到表")
        @PutMapping(value = "/{id}")
        public Result updateCheckIn(@Parameter(description = "讲座签到表ID") @PathVariable Long id,
        @RequestBody @Validated CheckIn formData) {
            boolean result = checkInService.updateCheckIn(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除讲座签到表")
        @DeleteMapping("/{ids}")
        public Result deleteCheckIns(
            @Parameter(description = "讲座签到表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = checkInService.deleteCheckIns(ids);
            return Result.judge(result);
        }
}

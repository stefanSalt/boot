package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.Activity;
import com.lee.system.service.ActivityService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 活动表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "活动表接口")
@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

        private final ActivityService activityService;

        @Operation(summary = "活动表 分页列表")
        @GetMapping("/page")
        public Result listPagedActivitys(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Activity queryParams ) {
            IPage<Activity> result = activityService.listPagedActivitys(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增活动表")
        @PostMapping
        public Result saveActivity(@RequestBody @Valid Activity formData ) {
            boolean result = activityService.saveActivity(formData);
            return Result.judge(result);
        }

        @Operation(summary = "活动表表单数据")
        @GetMapping("/{id}/form")
        public Result<Activity> getActivity(
            @Parameter(description = "活动表ID") @PathVariable Long id
        ) {
            Activity formData = activityService.getActivityData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改活动表")
        @PutMapping(value = "/{id}")
        public Result updateActivity(@Parameter(description = "活动表ID") @PathVariable Long id,
        @RequestBody @Validated Activity formData) {
            boolean result = activityService.updateActivity(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除活动表")
        @DeleteMapping("/{ids}")
        public Result deleteActivitys(
            @Parameter(description = "活动表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = activityService.deleteActivitys(ids);
            return Result.judge(result);
        }
}

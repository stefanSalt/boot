package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.ActivityRegistration;
import com.lee.system.service.ActivityRegistrationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 活动报名表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "活动报名表接口")
@RestController
@RequestMapping("/api/v1/activity-registrations")
@RequiredArgsConstructor
public class ActivityRegistrationController {

        private final ActivityRegistrationService activityRegistrationService;

        @Operation(summary = "活动报名表 分页列表")
        @GetMapping("/page")
        public Result listPagedActivityRegistrations(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        ActivityRegistration queryParams ) {
            IPage<ActivityRegistration> result = activityRegistrationService.listPagedActivityRegistrations(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增活动报名表")
        @PostMapping
        public Result saveActivityRegistration(@RequestBody @Valid ActivityRegistration formData ) {
            boolean result = activityRegistrationService.saveActivityRegistration(formData);
            return Result.judge(result);
        }

        @Operation(summary = "活动报名表表单数据")
        @GetMapping("/{id}/form")
        public Result<ActivityRegistration> getActivityRegistration(
            @Parameter(description = "活动报名表ID") @PathVariable Long id
        ) {
            ActivityRegistration formData = activityRegistrationService.getActivityRegistrationData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改活动报名表")
        @PutMapping(value = "/{id}")
        public Result updateActivityRegistration(@Parameter(description = "活动报名表ID") @PathVariable Long id,
        @RequestBody @Validated ActivityRegistration formData) {
            boolean result = activityRegistrationService.updateActivityRegistration(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除活动报名表")
        @DeleteMapping("/{ids}")
        public Result deleteActivityRegistrations(
            @Parameter(description = "活动报名表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = activityRegistrationService.deleteActivityRegistrations(ids);
            return Result.judge(result);
        }
}

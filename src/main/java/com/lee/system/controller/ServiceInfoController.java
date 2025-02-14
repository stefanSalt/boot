package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.ServiceInfo;
import com.lee.system.service.ServiceInfoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 服务表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "服务表接口")
@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceInfoController {

        private final ServiceInfoService serviceInfoService;

        @Operation(summary = "服务表 分页列表")
        @GetMapping("/page")
        public Result listPagedServiceInfos(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        ServiceInfo queryParams ) {
            IPage<ServiceInfo> result = serviceInfoService.listPagedServiceInfos(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增服务表")
        @PostMapping
        public Result saveServiceInfo(@RequestBody @Valid ServiceInfo formData ) {
            boolean result = serviceInfoService.saveServiceInfo(formData);
            return Result.judge(result);
        }

        @Operation(summary = "服务表表单数据")
        @GetMapping("/{id}/form")
        public Result<ServiceInfo> getServiceInfo(
            @Parameter(description = "服务表ID") @PathVariable Long id
        ) {
            ServiceInfo formData = serviceInfoService.getServiceInfoData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改服务表")
        @PutMapping(value = "/{id}")
        public Result updateServiceInfo(@Parameter(description = "服务表ID") @PathVariable Long id,
        @RequestBody @Validated ServiceInfo formData) {
            boolean result = serviceInfoService.updateServiceInfo(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除服务表")
        @DeleteMapping("/{ids}")
        public Result deleteServiceInfos(
            @Parameter(description = "服务表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = serviceInfoService.deleteServiceInfos(ids);
            return Result.judge(result);
        }
}

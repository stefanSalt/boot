package com.lee.reservation.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.reservation.common.result.PageResult;
import com.lee.reservation.common.result.Result;
import com.lee.reservation.system.model.form.VehicleForm;
import com.lee.reservation.system.model.query.VehiclePageQuery;
import com.lee.reservation.system.model.vo.VehiclePageVO;
import com.lee.reservation.system.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 车辆信息 前端控制器
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Tag(name = "车辆信息接口")
@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

        private final VehicleService vehicleService;

        @Operation(summary = "车辆信息 分页列表")
        @GetMapping("/page")
        public PageResult<VehiclePageVO> listPagedVehicles(VehiclePageQuery queryParams ) {
            IPage<VehiclePageVO> result = vehicleService.listPagedVehicles(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增车辆信息")
        @PostMapping
        public Result saveVehicle(@RequestBody @Valid VehicleForm formData ) {
            boolean result = vehicleService.saveVehicle(formData);
            return Result.judge(result);
        }

        @Operation(summary = "车辆信息表单数据")
        @GetMapping("/{id}/form")
        public Result<VehicleForm> getVehicleForm(
            @Parameter(description = "车辆信息ID") @PathVariable Long id
        ) {
            VehicleForm formData = vehicleService.getVehicleFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改车辆信息")
        @PutMapping(value = "/{id}")
        public Result updateVehicle(@Parameter(description = "车辆信息ID") @PathVariable Long id,
        @RequestBody @Validated VehicleForm formData) {
            boolean result = vehicleService.updateVehicle(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除车辆信息")
        @DeleteMapping("/{ids}")
        public Result deleteVehicles(
            @Parameter(description = "车辆信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = vehicleService.deleteVehicles(ids);
            return Result.judge(result);
        }
}

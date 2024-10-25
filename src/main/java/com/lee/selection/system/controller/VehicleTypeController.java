package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.entity.VehicleType;
import com.lee.selection.system.model.form.VehicleTypeForm;
import com.lee.selection.system.model.query.VehicleTypePageQuery;
import com.lee.selection.system.model.vo.VehicleTypePageVO;
import com.lee.selection.system.service.VehicleTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 车辆类型 前端控制器
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Tag(name = "车辆类型接口")
@RestController
@RequestMapping("/api/v1/vehicleTypes")
@RequiredArgsConstructor
public class VehicleTypeController {

        private final VehicleTypeService vehicleTypeService;

        @Operation(summary = "车辆类型 分页列表")
        @GetMapping("/page")
        public PageResult<VehicleTypePageVO> listPagedVehicleTypes(VehicleTypePageQuery queryParams ) {
            IPage<VehicleTypePageVO> result = vehicleTypeService.listPagedVehicleTypes(queryParams);
            return PageResult.success(result);
        }

        //列表
    @Operation(summary = "车辆类型列表")
    @GetMapping("/list")
    public Result listMyPagedVehicleTypes( ) {
        List<VehicleType> result = vehicleTypeService.list();
        return Result.success(result);
    }

        @Operation(summary = "新增车辆类型")
        @PostMapping
        public Result saveVehicleType(@RequestBody @Valid VehicleTypeForm formData ) {
            boolean result = vehicleTypeService.saveVehicleType(formData);
            return Result.judge(result);
        }

        @Operation(summary = "车辆类型表单数据")
        @GetMapping("/{id}/form")
        public Result<VehicleTypeForm> getVehicleTypeForm(
            @Parameter(description = "车辆类型ID") @PathVariable Long id
        ) {
            VehicleTypeForm formData = vehicleTypeService.getVehicleTypeFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改车辆类型")
        @PutMapping(value = "/{id}")
        public Result updateVehicleType(@Parameter(description = "车辆类型ID") @PathVariable Long id,
        @RequestBody @Validated VehicleTypeForm formData) {
            boolean result = vehicleTypeService.updateVehicleType(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除车辆类型")
        @DeleteMapping("/{ids}")
        public Result deleteVehicleTypes(
            @Parameter(description = "车辆类型ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = vehicleTypeService.deleteVehicleTypes(ids);
            return Result.judge(result);
        }
}

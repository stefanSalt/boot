package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Warehouse;
import com.lee.warehouse.system.service.WarehouseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 仓库信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Tag(name = "仓库信息接口")
@RestController
@RequestMapping("/api/v1/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

        private final WarehouseService warehouseService;

        @Operation(summary = "仓库信息 分页列表")
        @GetMapping("/page")
        public Result listPagedWarehouses(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Warehouse queryParams ) {
            IPage<Warehouse> result = warehouseService.listPagedWarehouses(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "仓库列表option")
        @GetMapping("/options")
        public Result listWarehouseOptions() {
            return Result.success(warehouseService.listWarehouseOptions());
        }

        @Operation(summary = "新增仓库信息")
        @PostMapping
        public Result saveWarehouse(@RequestBody @Valid Warehouse formData ) {
            boolean result = warehouseService.saveWarehouse(formData);
            return Result.judge(result);
        }

        @Operation(summary = "仓库信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Warehouse> getWarehouse(
            @Parameter(description = "仓库信息ID") @PathVariable Long id
        ) {
            Warehouse formData = warehouseService.getWarehouseData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改仓库信息")
        @PutMapping(value = "/{id}")
        public Result updateWarehouse(@Parameter(description = "仓库信息ID") @PathVariable Long id,
        @RequestBody @Validated Warehouse formData) {
            boolean result = warehouseService.updateWarehouse(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除仓库信息")
        @DeleteMapping("/{ids}")
        public Result deleteWarehouses(
            @Parameter(description = "仓库信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = warehouseService.deleteWarehouses(ids);
            return Result.judge(result);
        }
}

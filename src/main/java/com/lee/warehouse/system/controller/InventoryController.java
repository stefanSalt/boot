package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Inventory;
import com.lee.warehouse.system.service.InventoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 库存信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Tag(name = "库存信息接口")
@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {

        private final InventoryService inventoryService;

        @Operation(summary = "库存信息 分页列表")
        @GetMapping("/page")
        public Result listPagedInventorys(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Inventory queryParams ) {
            IPage<Inventory> result = inventoryService.listPagedInventorys(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增库存信息")
        @PostMapping
        public Result saveInventory(@RequestBody @Valid Inventory formData ) {
            boolean result = inventoryService.saveInventory(formData);
            return Result.judge(result);
        }

        @Operation(summary = "库存信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Inventory> getInventory(
            @Parameter(description = "库存信息ID") @PathVariable Long id
        ) {
            Inventory formData = inventoryService.getInventoryData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改库存信息")
        @PutMapping(value = "/{id}")
        public Result updateInventory(@Parameter(description = "库存信息ID") @PathVariable Long id,
        @RequestBody @Validated Inventory formData) {
            boolean result = inventoryService.updateInventory(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除库存信息")
        @DeleteMapping("/{ids}")
        public Result deleteInventorys(
            @Parameter(description = "库存信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = inventoryService.deleteInventorys(ids);
            return Result.judge(result);
        }
}

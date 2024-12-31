package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.InventoryTransaction;
import com.lee.warehouse.system.service.InventoryTransactionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 库存交易信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-26
 */
@Tag(name = "库存交易信息接口")
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class InventoryTransactionController {

        private final InventoryTransactionService inventoryTransactionService;

        @Operation(summary = "库存交易信息 分页列表")
        @GetMapping("/page")
        public Result listPagedInventoryTransactions(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        InventoryTransaction queryParams ) {
            IPage<InventoryTransaction> result = inventoryTransactionService.listPagedInventoryTransactions(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增库存交易信息")
        @PostMapping
        public Result saveInventoryTransaction(@RequestBody @Valid InventoryTransaction formData ) {
            boolean result = inventoryTransactionService.saveInventoryTransaction(formData);
            return Result.judge(result);
        }

        @Operation(summary = "库存交易信息表单数据")
        @GetMapping("/{id}/form")
        public Result<InventoryTransaction> getInventoryTransaction(
            @Parameter(description = "库存交易信息ID") @PathVariable Long id
        ) {
            InventoryTransaction formData = inventoryTransactionService.getInventoryTransactionData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改库存交易信息")
        @PutMapping(value = "/{id}")
        public Result updateInventoryTransaction(@Parameter(description = "库存交易信息ID") @PathVariable Long id,
        @RequestBody @Validated InventoryTransaction formData) {
            boolean result = inventoryTransactionService.updateInventoryTransaction(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除库存交易信息")
        @DeleteMapping("/{ids}")
        public Result deleteInventoryTransactions(
            @Parameter(description = "库存交易信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = inventoryTransactionService.deleteInventoryTransactions(ids);
            return Result.judge(result);
        }
}

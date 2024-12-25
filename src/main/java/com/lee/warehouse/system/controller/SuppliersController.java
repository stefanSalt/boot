package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Suppliers;
import com.lee.warehouse.system.service.SuppliersService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 供应商信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-24
 */
@Tag(name = "供应商信息接口")
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SuppliersController {

        private final SuppliersService suppliersService;

        @Operation(summary = "供应商信息 分页列表")
        @GetMapping("/page")
        public Result listPagedSupplierss(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10")Integer pageSize,
                                          Suppliers queryParams ) {
            IPage<Suppliers> result = suppliersService.listPagedSupplierss(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "供应商列表option")
        @GetMapping("/options")
        public Result listSuppliersOptions() {
            return Result.success(suppliersService.listSuppliersOptions());
        }

        @Operation(summary = "新增供应商信息")
        @PostMapping
        public Result saveSuppliers(@RequestBody @Valid Suppliers formData ) {
            boolean result = suppliersService.saveSuppliers(formData);
            return Result.judge(result);
        }

        @Operation(summary = "供应商信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Suppliers> getSuppliers(
            @Parameter(description = "供应商信息ID") @PathVariable Long id
        ) {
            Suppliers formData = suppliersService.getSuppliersData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改供应商信息")
        @PutMapping(value = "/{id}")
        public Result updateSuppliers(@Parameter(description = "供应商信息ID") @PathVariable Long id,
        @RequestBody @Validated Suppliers formData) {
            boolean result = suppliersService.updateSuppliers(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除供应商信息")
        @DeleteMapping("/{ids}")
        public Result deleteSuppliers(
            @Parameter(description = "供应商信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = suppliersService.deleteSuppliers(ids);
            return Result.judge(result);
        }
}

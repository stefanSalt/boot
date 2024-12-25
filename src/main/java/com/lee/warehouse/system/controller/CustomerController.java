package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Customer;
import com.lee.warehouse.system.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 客户信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Tag(name = "客户信息接口")
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

        private final CustomerService customerService;

        @Operation(summary = "客户信息 分页列表")
        @GetMapping("/page")
        public Result listPagedCustomers(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10")Integer pageSize,
                                         Customer queryParams ) {
            IPage<Customer> result = customerService.listPagedCustomers(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "客户列表option")
        @GetMapping("/options")
        public Result listCustomerOptions() {
            return Result.success(customerService.listCustomerOptions());
        }

        @Operation(summary = "新增客户信息")
        @PostMapping
        public Result saveCustomer(@RequestBody @Valid Customer formData ) {
            boolean result = customerService.saveCustomer(formData);
            return Result.judge(result);
        }

        @Operation(summary = "客户信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Customer> getCustomer(
            @Parameter(description = "客户信息ID") @PathVariable Long id
        ) {
            Customer formData = customerService.getCustomerData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改客户信息")
        @PutMapping(value = "/{id}")
        public Result updateCustomer(@Parameter(description = "客户信息ID") @PathVariable Long id,
        @RequestBody @Validated Customer formData) {
            boolean result = customerService.updateCustomer(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除客户信息")
        @DeleteMapping("/{ids}")
        public Result deleteCustomers(
            @Parameter(description = "客户信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = customerService.deleteCustomers(ids);
            return Result.judge(result);
        }
}

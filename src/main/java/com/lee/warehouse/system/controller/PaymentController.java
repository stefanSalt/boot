package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Payment;
import com.lee.warehouse.system.service.PaymentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 支付信息 前端控制器
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Tag(name = "支付信息接口")
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

        private final PaymentService paymentService;

        @Operation(summary = "支付信息 分页列表")
        @GetMapping("/page")
        public Result listPagedPayments(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Payment queryParams ) {
            IPage<Payment> result = paymentService.listPagedPayments(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增支付信息")
        @PostMapping
        public Result savePayment(@RequestBody @Valid Payment formData ) {
            boolean result = paymentService.savePayment(formData);
            return Result.judge(result);
        }

        @Operation(summary = "支付信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Payment> getPayment(
            @Parameter(description = "支付信息ID") @PathVariable Long id
        ) {
            Payment formData = paymentService.getPaymentData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改支付信息")
        @PutMapping(value = "/{id}")
        public Result updatePayment(@Parameter(description = "支付信息ID") @PathVariable Long id,
        @RequestBody @Validated Payment formData) {
            boolean result = paymentService.updatePayment(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除支付信息")
        @DeleteMapping("/{ids}")
        public Result deletePayments(
            @Parameter(description = "支付信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = paymentService.deletePayments(ids);
            return Result.judge(result);
        }
}

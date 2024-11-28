package com.lee.online_store.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.online_store.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.online_store.system.model.entity.OrderProduct;
import com.lee.online_store.system.service.OrderProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 订单商品关联 前端控制器
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Tag(name = "订单商品关联接口")
@RestController
@RequestMapping("/api/v1/orderProducts")
@RequiredArgsConstructor
public class OrderProductController {

        private final OrderProductService orderProductService;

        @Operation(summary = "订单商品关联 分页列表")
        @GetMapping("/page")
        public Result listPagedOrderProducts(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        OrderProduct queryParams ) {
            IPage<OrderProduct> result = orderProductService.listPagedOrderProducts(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增订单商品关联")
        @PostMapping
        public Result saveOrderProduct(@RequestBody @Valid OrderProduct formData ) {
            boolean result = orderProductService.saveOrderProduct(formData);
            return Result.judge(result);
        }

        @Operation(summary = "订单商品关联表单数据")
        @GetMapping("/{id}/form")
        public Result<OrderProduct> getOrderProduct(
            @Parameter(description = "订单商品关联ID") @PathVariable Long id
        ) {
            OrderProduct formData = orderProductService.getOrderProductData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改订单商品关联")
        @PutMapping(value = "/{id}")
        public Result updateOrderProduct(@Parameter(description = "订单商品关联ID") @PathVariable Long id,
        @RequestBody @Validated OrderProduct formData) {
            boolean result = orderProductService.updateOrderProduct(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除订单商品关联")
        @DeleteMapping("/{ids}")
        public Result deleteOrderProducts(
            @Parameter(description = "订单商品关联ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = orderProductService.deleteOrderProducts(ids);
            return Result.judge(result);
        }
}

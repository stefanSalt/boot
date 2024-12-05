package com.lee.online_store.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.online_store.common.result.Result;
import com.lee.online_store.system.model.dto.OrderQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.online_store.system.model.entity.Order;
import com.lee.online_store.system.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 订单 前端控制器
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Tag(name = "订单接口")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

        private final OrderService orderService;

        @Operation(summary = "订单 分页列表")
        @GetMapping("/page")
        public Result listPagedOrders(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        OrderQuery queryParams ) {
            IPage<Order> result = orderService.listPagedOrders(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "订单列表")
        @GetMapping("/list")
        public Result listOrders(OrderQuery queryParams ) {
            List<Order> result = orderService.listOrders(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增订单")
        @PostMapping
        public Result saveOrder(@RequestBody @Valid Order formData ) {
            boolean result = orderService.saveOrder(formData);
            return Result.judge(result);
        }

        @Operation(summary = "订单表单数据")
        @GetMapping("/{id}/form")
        public Result<Order> getOrder(
            @Parameter(description = "订单ID") @PathVariable Long id
        ) {
            Order formData = orderService.getOrderData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改订单")
        @PutMapping(value = "/{id}")
        public Result updateOrder(@Parameter(description = "订单ID") @PathVariable Long id,
        @RequestBody @Validated Order formData) {
            boolean result = orderService.updateOrder(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除订单")
        @DeleteMapping("/{ids}")
        public Result deleteOrders(
            @Parameter(description = "订单ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = orderService.deleteOrders(ids);
            return Result.judge(result);
        }
}

package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.OrderDetail;
import com.lee.warehouse.system.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 订单详情信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Tag(name = "订单详情信息接口")
@RestController
@RequestMapping("/api/v1/orderDetails")
@RequiredArgsConstructor
public class OrderDetailController {

        private final OrderDetailService orderDetailService;

        @Operation(summary = "订单详情信息 分页列表")
        @GetMapping("/page")
        public Result listPagedOrderDetails(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        OrderDetail queryParams ) {
            IPage<OrderDetail> result = orderDetailService.listPagedOrderDetails(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增订单详情信息")
        @PostMapping
        public Result saveOrderDetail(@RequestBody @Valid OrderDetail formData ) {
            boolean result = orderDetailService.saveOrderDetail(formData);
            return Result.judge(result);
        }

        @Operation(summary = "订单详情信息表单数据")
        @GetMapping("/{id}/form")
        public Result<OrderDetail> getOrderDetail(
            @Parameter(description = "订单详情信息ID") @PathVariable Long id
        ) {
            OrderDetail formData = orderDetailService.getOrderDetailData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改订单详情信息")
        @PutMapping(value = "/{id}")
        public Result updateOrderDetail(@Parameter(description = "订单详情信息ID") @PathVariable Long id,
        @RequestBody @Validated OrderDetail formData) {
            boolean result = orderDetailService.updateOrderDetail(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除订单详情信息")
        @DeleteMapping("/{ids}")
        public Result deleteOrderDetails(
            @Parameter(description = "订单详情信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = orderDetailService.deleteOrderDetails(ids);
            return Result.judge(result);
        }
}

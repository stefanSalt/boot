package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.Reservation;
import com.lee.system.service.ReservationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 预约记录表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "预约记录表接口")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

        private final ReservationService reservationService;

        @Operation(summary = "预约记录表 分页列表")
        @GetMapping("/page")
        public Result listPagedReservations(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Reservation queryParams ) {
            IPage<Reservation> result = reservationService.listPagedReservations(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增预约记录表")
        @PostMapping
        public Result saveReservation(@RequestBody @Valid Reservation formData ) {
            boolean result = reservationService.saveReservation(formData);
            return Result.judge(result);
        }

        @Operation(summary = "预约记录表表单数据")
        @GetMapping("/{id}/form")
        public Result<Reservation> getReservation(
            @Parameter(description = "预约记录表ID") @PathVariable Long id
        ) {
            Reservation formData = reservationService.getReservationData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改预约记录表")
        @PutMapping(value = "/{id}")
        public Result updateReservation(@Parameter(description = "预约记录表ID") @PathVariable Long id,
        @RequestBody @Validated Reservation formData) {
            boolean result = reservationService.updateReservation(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除预约记录表")
        @DeleteMapping("/{ids}")
        public Result deleteReservations(
            @Parameter(description = "预约记录表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = reservationService.deleteReservations(ids);
            return Result.judge(result);
        }
}

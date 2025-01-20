package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.lecture.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.model.entity.Reservation;
import com.lee.lecture.system.service.ReservationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 讲座预约表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Tag(name = "讲座预约表接口")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

        private final ReservationService reservationService;

        @Operation(summary = "讲座预约表 分页列表")
        @GetMapping("/page")
        public Result listPagedReservations(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Reservation queryParams ) {
            IPage<Reservation> result = reservationService.listPagedReservations(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增讲座预约表")
        @PostMapping
        public Result saveReservation(@RequestBody @Valid Reservation formData ) {
            boolean result = reservationService.saveReservation(formData);
            return Result.judge(result);
        }

        @Operation(summary = "讲座预约表表单数据")
        @GetMapping("/{id}/form")
        public Result<Reservation> getReservation(
            @Parameter(description = "讲座预约表ID") @PathVariable Long id
        ) {
            Reservation formData = reservationService.getReservationData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改讲座预约表")
        @PutMapping(value = "/{id}")
        public Result updateReservation(@Parameter(description = "讲座预约表ID") @PathVariable Long id,
        @RequestBody @Validated Reservation formData) {
            boolean result = reservationService.updateReservation(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除讲座预约表")
        @DeleteMapping("/{ids}")
        public Result deleteReservations(
            @Parameter(description = "讲座预约表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = reservationService.deleteReservations(ids);
            return Result.judge(result);
        }
}

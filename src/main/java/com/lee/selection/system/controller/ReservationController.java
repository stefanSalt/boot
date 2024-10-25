package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.ReservationForm;
import com.lee.selection.system.model.query.ReservationCalendarQuery;
import com.lee.selection.system.model.query.ReservationPageQuery;
import com.lee.selection.system.model.vo.ReservationPageVO;
import com.lee.selection.system.model.vo.ReservationVO;
import com.lee.selection.system.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 预约记录 前端控制器
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Tag(name = "预约记录接口")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "预约记录 分页列表")
    @GetMapping("/page")
    public PageResult<ReservationPageVO> listPagedReservations(ReservationPageQuery queryParams) {
        IPage<ReservationPageVO> result = reservationService.listPagedReservations(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "预约记录 分页列表")
    @GetMapping("/student-page")
    public PageResult<ReservationPageVO> listStudentPagedReservations(ReservationPageQuery queryParams) {
        IPage<ReservationPageVO> result = reservationService.listStudentPagedReservations(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "预约记录 分页列表")
    @GetMapping("/instructor-page")
    public PageResult<ReservationPageVO> listInstructorPagedReservations(ReservationPageQuery queryParams) {
        IPage<ReservationPageVO> result = reservationService.listInstructorPagedReservations(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "预约信息详情")
    @GetMapping("/{id}/detail")
    public Result<ReservationVO> getCourseDetail(
            @Parameter(description = "预约信息ID") @PathVariable  Long id
    ) {
        ReservationVO detail = reservationService.getReservationDetail(id);
        return Result.success(detail);
    }

    //日历
    @Operation(summary = "日历")
    @GetMapping("/calendar")
    public Result<List<ReservationVO>> getCalendar(
            ReservationCalendarQuery queryParams
    ) {
        List<ReservationVO> calendar = reservationService.getReservationCalendar(queryParams);
        return Result.success(calendar);
    }




    @Operation(summary = "新增预约记录")
    @PostMapping
    public Result saveReservation(@RequestBody @Valid ReservationForm formData) {
        boolean result = reservationService.saveReservation(formData);
        return Result.judge(result);
    }

    @Operation(summary = "预约记录表单数据")
    @GetMapping("/{id}/form")
    public Result<ReservationForm> getReservationForm(
            @Parameter(description = "预约记录ID") @PathVariable Long id
    ) {
        ReservationForm formData = reservationService.getReservationFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改预约记录")
    @PutMapping(value = "/{id}")
    public Result updateReservation(@Parameter(description = "预约记录ID") @PathVariable Long id,
                                    @RequestBody @Validated ReservationForm formData) {
        boolean result = reservationService.updateReservation(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "通过预约记录" )
    @PutMapping("/{id}/pass")
    public Result passReservation(@Parameter(description = "预约记录ID") @PathVariable Long id) {
        boolean result = reservationService.passReservation(id);
        return Result.judge(result);
    }

    @Operation(summary = "拒绝预约记录")
    @PutMapping("/{id}/refuse")
    public Result refuseReservation(@Parameter(description = "预约记录ID") @PathVariable Long id) {
        boolean result = reservationService.refuseReservation(id);
        return Result.judge(result);
    }

    @Operation(summary = "取消预约记录")
    @PutMapping("/{id}/cancel")
    public Result cancelReservation(@Parameter(description = "预约记录ID") @PathVariable Long id) {
        boolean result = reservationService.cancelReservation(id);
        return Result.judge(result);
    }


    @Operation(summary = "删除预约记录")
    @DeleteMapping("/{ids}")
    public Result deleteReservations(
            @Parameter(description = "预约记录ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = reservationService.deleteReservations(ids);
        return Result.judge(result);
    }
}

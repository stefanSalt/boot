package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.common.util.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.ApplyForm;
import com.lee.selection.system.model.query.ApplyPageQuery;
import com.lee.selection.system.model.vo.ApplyPageVO;
import com.lee.selection.system.service.ApplyService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 申请表 前端控制器
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Tag(name = "申请表接口")
@RestController
@RequestMapping("/api/v1/applys")
@RequiredArgsConstructor
public class ApplyController {

        private final ApplyService applyService;

        @Operation(summary = "申请表 分页列表")
        @GetMapping("/page")
        public PageResult<ApplyPageVO> listPagedApplys(ApplyPageQuery queryParams ) {
            IPage<ApplyPageVO> result = applyService.listPagedApplys(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "申请表 学生分页列表")
        @GetMapping("/student/page")
        public PageResult<ApplyPageVO> listPagedApplysByStudent(ApplyPageQuery queryParams ) {
            IPage<ApplyPageVO> result = applyService.listPagedApplysByStudent(queryParams);
            return PageResult.success(result);
        }

    @Operation(summary = "申请表 教师分页列表")
    @GetMapping("/teacher/page")
    public PageResult<ApplyPageVO> listPagedApplysByTeacher(ApplyPageQuery queryParams ) {
        IPage<ApplyPageVO> result = applyService.listPagedApplysByTeacher(queryParams);
        return PageResult.success(result);
    }



        @Operation(summary = "新增申请表")
        @PostMapping
        public Result saveApply(@RequestBody @Valid ApplyForm formData ) {
            boolean result = applyService.saveApply(formData);
            return Result.judge(result);
        }

        @Operation(summary = "申请表表单数据")
        @GetMapping("/{id}/form")
        public Result<ApplyForm> getApplyForm(
            @Parameter(description = "申请表ID") @PathVariable Long id
        ) {
            ApplyForm formData = applyService.getApplyFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改申请表")
        @PutMapping(value = "/{id}")
        public Result updateApply(@Parameter(description = "申请表ID") @PathVariable Long id,
        @RequestBody @Validated ApplyForm formData) {
            boolean result = applyService.updateApply(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除申请表")
        @DeleteMapping("/{ids}")
        public Result deleteApplys(
            @Parameter(description = "申请表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = applyService.deleteApplys(ids);
            return Result.judge(result);
        }
}

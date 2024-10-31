package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.vo.MajorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.MajorForm;
import com.lee.selection.system.model.query.MajorPageQuery;
import com.lee.selection.system.model.vo.MajorPageVO;
import com.lee.selection.system.service.MajorService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 专业信息表 前端控制器
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Tag(name = "专业信息表接口")
@RestController
@RequestMapping("/api/v1/majors")
@RequiredArgsConstructor
public class MajorController {

        private final MajorService majorService;

        @Operation(summary = "专业信息表 分页列表")
        @GetMapping("/page")
        public PageResult<MajorPageVO> listPagedMajors(MajorPageQuery queryParams ) {
            IPage<MajorPageVO> result = majorService.listPagedMajors(queryParams);
            return PageResult.success(result);
        }


        @Operation(summary = "专业信息 列表")
        @GetMapping("/list")
        public Result<List<MajorVO>> listMajors(MajorPageQuery queryParams ) {
            List<MajorVO> result = majorService.listMajors(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增专业信息表")
        @PostMapping
        public Result saveMajor(@RequestBody @Valid MajorForm formData ) {
            boolean result = majorService.saveMajor(formData);
            return Result.judge(result);
        }

        @Operation(summary = "专业信息表表单数据")
        @GetMapping("/{id}/form")
        public Result<MajorForm> getMajorForm(
            @Parameter(description = "专业信息表ID") @PathVariable Long id
        ) {
            MajorForm formData = majorService.getMajorFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改专业信息表")
        @PutMapping(value = "/{id}")
        public Result updateMajor(@Parameter(description = "专业信息表ID") @PathVariable Long id,
        @RequestBody @Validated MajorForm formData) {
            boolean result = majorService.updateMajor(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除专业信息表")
        @DeleteMapping("/{ids}")
        public Result deleteMajors(
            @Parameter(description = "专业信息表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = majorService.deleteMajors(ids);
            return Result.judge(result);
        }
}

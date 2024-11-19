package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Recruiter;
import com.lee.selection.system.service.RecruiterService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 招聘者 前端控制器
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Tag(name = "招聘者接口")
@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {

        private final RecruiterService recruiterService;

        @Operation(summary = "招聘者 分页列表")
        @GetMapping("/page")
        public Result listPagedRecruiters(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Recruiter queryParams ) {
            IPage<Recruiter> result = recruiterService.listPagedRecruiters(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增招聘者")
        @PostMapping
        public Result saveRecruiter(@RequestBody @Valid Recruiter formData ) {
            boolean result = recruiterService.saveRecruiter(formData);
            return Result.judge(result);
        }

        @Operation(summary = "招聘者表单数据")
        @GetMapping("/{id}/form")
        public Result<Recruiter> getRecruiter(
            @Parameter(description = "招聘者ID") @PathVariable Long id
        ) {
            Recruiter formData = recruiterService.getRecruiterData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改招聘者")
        @PutMapping(value = "/{id}")
        public Result updateRecruiter(@Parameter(description = "招聘者ID") @PathVariable Long id,
        @RequestBody @Validated Recruiter formData) {
            boolean result = recruiterService.updateRecruiter(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除招聘者")
        @DeleteMapping("/{ids}")
        public Result deleteRecruiters(
            @Parameter(description = "招聘者ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = recruiterService.deleteRecruiters(ids);
            return Result.judge(result);
        }
}

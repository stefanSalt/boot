package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.QuestionOption;
import com.lee.questionnaire.system.service.QuestionOptionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 选项表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Tag(name = "选项表接口")
@RestController
@RequestMapping("/api/v1/questionOptions")
@RequiredArgsConstructor
public class QuestionOptionController {

        private final QuestionOptionService questionOptionService;

        @Operation(summary = "选项表 分页列表")
        @GetMapping("/page")
        public Result listPagedQuestionOptions(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        QuestionOption queryParams ) {
            IPage<QuestionOption> result = questionOptionService.listPagedQuestionOptions(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增选项表")
        @PostMapping
        public Result saveQuestionOption(@RequestBody @Valid QuestionOption formData ) {
            boolean result = questionOptionService.saveQuestionOption(formData);
            return Result.judge(result);
        }

        @Operation(summary = "选项表表单数据")
        @GetMapping("/{id}/form")
        public Result<QuestionOption> getQuestionOption(
            @Parameter(description = "选项表ID") @PathVariable Long id
        ) {
            QuestionOption formData = questionOptionService.getQuestionOptionData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改选项表")
        @PutMapping(value = "/{id}")
        public Result updateQuestionOption(@Parameter(description = "选项表ID") @PathVariable Long id,
        @RequestBody @Validated QuestionOption formData) {
            boolean result = questionOptionService.updateQuestionOption(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除选项表")
        @DeleteMapping("/{ids}")
        public Result deleteQuestionOptions(
            @Parameter(description = "选项表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = questionOptionService.deleteQuestionOptions(ids);
            return Result.judge(result);
        }
}

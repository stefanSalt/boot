package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.QuestionnaireTemplate;
import com.lee.questionnaire.system.service.QuestionnaireTemplateService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 问卷模板表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Tag(name = "问卷模板表接口")
@RestController
@RequestMapping("/api/v1/questionnaireTemplates")
@RequiredArgsConstructor
public class QuestionnaireTemplateController {

        private final QuestionnaireTemplateService questionnaireTemplateService;

        @Operation(summary = "问卷模板表 分页列表")
        @GetMapping("/page")
        public Result listPagedQuestionnaireTemplates(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        QuestionnaireTemplate queryParams ) {
            IPage<QuestionnaireTemplate> result = questionnaireTemplateService.listPagedQuestionnaireTemplates(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增问卷模板表")
        @PostMapping
        public Result saveQuestionnaireTemplate(@RequestBody @Valid QuestionnaireTemplate formData ) {
            boolean result = questionnaireTemplateService.saveQuestionnaireTemplate(formData);
            return Result.judge(result);
        }

        @Operation(summary = "问卷模板表表单数据")
        @GetMapping("/{id}/form")
        public Result<QuestionnaireTemplate> getQuestionnaireTemplate(
            @Parameter(description = "问卷模板表ID") @PathVariable Long id
        ) {
            QuestionnaireTemplate formData = questionnaireTemplateService.getQuestionnaireTemplateData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改问卷模板表")
        @PutMapping(value = "/{id}")
        public Result updateQuestionnaireTemplate(@Parameter(description = "问卷模板表ID") @PathVariable Long id,
        @RequestBody @Validated QuestionnaireTemplate formData) {
            boolean result = questionnaireTemplateService.updateQuestionnaireTemplate(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除问卷模板表")
        @DeleteMapping("/{ids}")
        public Result deleteQuestionnaireTemplates(
            @Parameter(description = "问卷模板表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = questionnaireTemplateService.deleteQuestionnaireTemplates(ids);
            return Result.judge(result);
        }
}

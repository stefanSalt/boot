package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.Questionnaire;
import com.lee.questionnaire.system.service.QuestionnaireService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 问卷表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Tag(name = "问卷表接口")
@RestController
@RequestMapping("/api/v1/questionnaires")
@RequiredArgsConstructor
public class QuestionnaireController {

        private final QuestionnaireService questionnaireService;

        @Operation(summary = "问卷表 分页列表")
        @GetMapping("/page")
        public Result listPagedQuestionnaires(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Questionnaire queryParams ) {
            IPage<Questionnaire> result = questionnaireService.listPagedQuestionnaires(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

    @Operation(summary = "问卷表 前台 分页列表")
    @GetMapping("/front/page")
    public Result listFrontPagedQuestionnaires(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10")Integer pageSize,
                                          Questionnaire queryParams ) {
        IPage<Questionnaire> result = questionnaireService.listFrontPagedQuestionnaires(queryParams,pageNum,pageSize);
        return Result.success(result);
    }

        @Operation(summary = "新增问卷表")
        @PostMapping
        public Result saveQuestionnaire(@RequestBody @Valid Questionnaire formData ) {
            Integer result = questionnaireService.saveQuestionnaire(formData);
            return Result.success(result);
        }

        @Operation(summary = "使用模板创建问卷")
        @PostMapping("/{id}/template")
        public Result saveQuestionnaireByTemplate(@Parameter(description = "问卷表ID") @PathVariable Long id) {
            boolean result = questionnaireService.saveQuestionnaireByTemplate(id);
            return Result.judge(result);
        }

        @Operation(summary = "问卷表表单数据")
        @GetMapping("/{id}/form")
        public Result<Questionnaire> getQuestionnaire(
            @Parameter(description = "问卷表ID") @PathVariable Long id
        ) {
            Questionnaire formData = questionnaireService.getQuestionnaireData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改问卷表")
        @PutMapping(value = "/{id}")
        public Result updateQuestionnaire(@Parameter(description = "问卷表ID") @PathVariable Long id,
        @RequestBody @Validated Questionnaire formData) {
            boolean result = questionnaireService.updateQuestionnaire(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "发布问卷")
        @PutMapping(value = "/{id}/publish")
        public Result publishQuestionnaire(@Parameter(description = "问卷表ID") @PathVariable Long id) {
            boolean result = questionnaireService.publishQuestionnaire(id);
            return Result.judge(result);
        }
    @Operation(summary = "关闭问卷")
    @PutMapping(value = "/{id}/close")
    public Result closeQuestionnaire(@Parameter(description = "问卷表ID") @PathVariable Long id) {
        boolean result = questionnaireService.closeQuestionnaire(id);
        return Result.judge(result);
    }

        @Operation(summary = "删除问卷表")
        @DeleteMapping("/{ids}")
        public Result deleteQuestionnaires(
            @Parameter(description = "问卷表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = questionnaireService.deleteQuestionnaires(ids);
            return Result.judge(result);
        }
}

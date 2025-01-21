package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.Question;
import com.lee.questionnaire.system.service.QuestionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 问题表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Tag(name = "问题表接口")
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

        private final QuestionService questionService;

        @Operation(summary = "问题表 分页列表")
        @GetMapping("/page")
        public Result listPagedQuestions(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Question queryParams ) {
            IPage<Question> result = questionService.listPagedQuestions(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增问题表")
        @PostMapping
        public Result saveQuestion(@RequestBody @Valid Question formData ) {
            boolean result = questionService.saveQuestion(formData);
            return Result.judge(result);
        }

        @Operation(summary = "问题表表单数据")
        @GetMapping("/{id}/form")
        public Result<Question> getQuestion(
            @Parameter(description = "问题表ID") @PathVariable Long id
        ) {
            Question formData = questionService.getQuestionData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改问题表")
        @PutMapping(value = "/{id}")
        public Result updateQuestion(@Parameter(description = "问题表ID") @PathVariable Long id,
        @RequestBody @Validated Question formData) {
            boolean result = questionService.updateQuestion(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除问题表")
        @DeleteMapping("/{ids}")
        public Result deleteQuestions(
            @Parameter(description = "问题表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = questionService.deleteQuestions(ids);
            return Result.judge(result);
        }
}

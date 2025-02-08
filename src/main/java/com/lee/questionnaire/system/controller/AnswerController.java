package com.lee.questionnaire.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.questionnaire.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.questionnaire.system.model.entity.Answer;
import com.lee.questionnaire.system.service.AnswerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 答案表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-07
 */
@Tag(name = "答案表接口")
@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {

        private final AnswerService answerService;

        @Operation(summary = "答案表 分页列表")
        @GetMapping("/page")
        public Result listPagedAnswers(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Answer queryParams ) {
            IPage<Answer> result = answerService.listPagedAnswers(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增答案表")
        @PostMapping
        public Result saveAnswer(@RequestBody @Valid Answer formData ) {
            boolean result = answerService.saveAnswer(formData);
            return Result.judge(result);
        }

        @Operation(summary = "答案表表单数据")
        @GetMapping("/{id}/form")
        public Result<Answer> getAnswer(
            @Parameter(description = "答案表ID") @PathVariable Long id
        ) {
            Answer formData = answerService.getAnswerData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改答案表")
        @PutMapping(value = "/{id}")
        public Result updateAnswer(@Parameter(description = "答案表ID") @PathVariable Long id,
        @RequestBody @Validated Answer formData) {
            boolean result = answerService.updateAnswer(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除答案表")
        @DeleteMapping("/{ids}")
        public Result deleteAnswers(
            @Parameter(description = "答案表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = answerService.deleteAnswers(ids);
            return Result.judge(result);
        }
}

package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.lecture.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.model.entity.Feedback;
import com.lee.lecture.system.service.FeedbackService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 讲座评价表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Tag(name = "讲座评价表接口")
@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

        private final FeedbackService feedbackService;

        @Operation(summary = "讲座评价表 分页列表")
        @GetMapping("/page")
        public Result listPagedFeedbacks(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Feedback queryParams ) {
            IPage<Feedback> result = feedbackService.listPagedFeedbacks(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增讲座评价表")
        @PostMapping
        public Result saveFeedback(@RequestBody @Valid Feedback formData ) {
            boolean result = feedbackService.saveFeedback(formData);
            return Result.judge(result);
        }

        @Operation(summary = "讲座评价表表单数据")
        @GetMapping("/{id}/form")
        public Result<Feedback> getFeedback(
            @Parameter(description = "讲座评价表ID") @PathVariable Long id
        ) {
            Feedback formData = feedbackService.getFeedbackData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改讲座评价表")
        @PutMapping(value = "/{id}")
        public Result updateFeedback(@Parameter(description = "讲座评价表ID") @PathVariable Long id,
        @RequestBody @Validated Feedback formData) {
            boolean result = feedbackService.updateFeedback(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除讲座评价表")
        @DeleteMapping("/{ids}")
        public Result deleteFeedbacks(
            @Parameter(description = "讲座评价表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = feedbackService.deleteFeedbacks(ids);
            return Result.judge(result);
        }
}

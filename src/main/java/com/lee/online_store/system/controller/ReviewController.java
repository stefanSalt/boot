package com.lee.online_store.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.online_store.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.online_store.system.model.entity.Review;
import com.lee.online_store.system.service.ReviewService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 评论 前端控制器
 *
 * @author baomidou
 * @since 2024-12-05
 */
@Tag(name = "评论接口")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

        private final ReviewService reviewService;

        @Operation(summary = "评论 分页列表")
        @GetMapping("/page")
        public Result listPagedReviews(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Review queryParams ) {
            IPage<Review> result = reviewService.listPagedReviews(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "评论列表")
        @GetMapping("/list")
        public Result listReviews(Review queryParams ) {
            List<Review> result = reviewService.listReviews(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增评论")
        @PostMapping
        public Result saveReview(@RequestBody @Valid Review formData ) {
            boolean result = reviewService.saveReview(formData);
            return Result.judge(result);
        }

        @Operation(summary = "评论表单数据")
        @GetMapping("/{id}/form")
        public Result<Review> getReview(
            @Parameter(description = "评论ID") @PathVariable Long id
        ) {
            Review formData = reviewService.getReviewData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改评论")
        @PutMapping(value = "/{id}")
        public Result updateReview(@Parameter(description = "评论ID") @PathVariable Long id,
        @RequestBody @Validated Review formData) {
            boolean result = reviewService.updateReview(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除评论")
        @DeleteMapping("/{ids}")
        public Result deleteReviews(
            @Parameter(description = "评论ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = reviewService.deleteReviews(ids);
            return Result.judge(result);
        }
}

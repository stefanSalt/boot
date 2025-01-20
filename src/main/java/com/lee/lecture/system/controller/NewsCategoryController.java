package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.lecture.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.model.entity.NewsCategory;
import com.lee.lecture.system.service.NewsCategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 资讯分类表 前端控制器
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Tag(name = "资讯分类表接口")
@RestController
@RequestMapping("/api/v1/newsCategorys")
@RequiredArgsConstructor
public class NewsCategoryController {

        private final NewsCategoryService newsCategoryService;

        @Operation(summary = "资讯分类表 分页列表")
        @GetMapping("/page")
        public Result listPagedNewsCategorys(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        NewsCategory queryParams ) {
            IPage<NewsCategory> result = newsCategoryService.listPagedNewsCategorys(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "资讯分类option")
        @GetMapping("/options")
        public Result listNewsCategoryOptions() {
            return Result.success(newsCategoryService.listNewsCategoryOptions());
        }

        @Operation(summary = "新增资讯分类表")
        @PostMapping
        public Result saveNewsCategory(@RequestBody @Valid NewsCategory formData ) {
            boolean result = newsCategoryService.saveNewsCategory(formData);
            return Result.judge(result);
        }

        @Operation(summary = "资讯分类表表单数据")
        @GetMapping("/{id}/form")
        public Result<NewsCategory> getNewsCategory(
            @Parameter(description = "资讯分类表ID") @PathVariable Long id
        ) {
            NewsCategory formData = newsCategoryService.getNewsCategoryData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改资讯分类表")
        @PutMapping(value = "/{id}")
        public Result updateNewsCategory(@Parameter(description = "资讯分类表ID") @PathVariable Long id,
        @RequestBody @Validated NewsCategory formData) {
            boolean result = newsCategoryService.updateNewsCategory(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除资讯分类表")
        @DeleteMapping("/{ids}")
        public Result deleteNewsCategorys(
            @Parameter(description = "资讯分类表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = newsCategoryService.deleteNewsCategorys(ids);
            return Result.judge(result);
        }
}

package com.lee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.system.model.entity.News;
import com.lee.system.service.NewsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 资讯表 前端控制器
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Tag(name = "资讯表接口")
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

        private final NewsService newsService;

        @Operation(summary = "资讯表 分页列表")
        @GetMapping("/page")
        public Result listPagedNewss(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        News queryParams ) {
            IPage<News> result = newsService.listPagedNewss(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增资讯表")
        @PostMapping
        public Result saveNews(@RequestBody @Valid News formData ) {
            boolean result = newsService.saveNews(formData);
            return Result.judge(result);
        }

        @Operation(summary = "资讯表表单数据")
        @GetMapping("/{id}/form")
        public Result<News> getNews(
            @Parameter(description = "资讯表ID") @PathVariable Long id
        ) {
            News formData = newsService.getNewsData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改资讯表")
        @PutMapping(value = "/{id}")
        public Result updateNews(@Parameter(description = "资讯表ID") @PathVariable Long id,
        @RequestBody @Validated News formData) {
            boolean result = newsService.updateNews(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除资讯表")
        @DeleteMapping("/{ids}")
        public Result deleteNewss(
            @Parameter(description = "资讯表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = newsService.deleteNewss(ids);
            return Result.judge(result);
        }
}

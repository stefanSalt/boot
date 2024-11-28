package com.lee.online_store.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.online_store.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.online_store.system.model.entity.Category;
import com.lee.online_store.system.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 商品分类 前端控制器
 *
 * @author baomidou
 * @since 2024-11-26
 */
@Tag(name = "商品分类接口")
@RestController
@RequestMapping("/api/v1/categorys")
@RequiredArgsConstructor
public class CategoryController {

        private final CategoryService categoryService;

        @Operation(summary = "商品分类列表")
        @GetMapping("/list")
        public Result listPagedCategorys(
                                         Category queryParams ) {
            List<Category> result = categoryService.listCategorys(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增商品分类")
        @PostMapping
        public Result saveCategory(@RequestBody @Valid Category formData ) {
            boolean result = categoryService.saveCategory(formData);
            return Result.judge(result);
        }

        @Operation(summary = "商品分类表单数据")
        @GetMapping("/{id}/form")
        public Result<Category> getCategory(
            @Parameter(description = "商品分类ID") @PathVariable Long id
        ) {
            Category formData = categoryService.getCategoryData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改商品分类")
        @PutMapping(value = "/{id}")
        public Result updateCategory(@Parameter(description = "商品分类ID") @PathVariable Long id,
        @RequestBody @Validated Category formData) {
            boolean result = categoryService.updateCategory(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除商品分类")
        @DeleteMapping("/{ids}")
        public Result deleteCategorys(
            @Parameter(description = "商品分类ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = categoryService.deleteCategorys(ids);
            return Result.judge(result);
        }
}

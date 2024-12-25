package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Product;
import com.lee.warehouse.system.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 产品信息 前端控制器
 *
 * @author baomidou
 * @since 2024-12-25
 */
@Tag(name = "产品信息接口")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

        private final ProductService productService;

        @Operation(summary = "产品信息 分页列表")
        @GetMapping("/page")
        public Result listPagedProducts(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Product queryParams ) {
            IPage<Product> result = productService.listPagedProducts(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增产品信息")
        @PostMapping
        public Result saveProduct(@RequestBody @Valid Product formData ) {
            boolean result = productService.saveProduct(formData);
            return Result.judge(result);
        }

        @Operation(summary = "产品信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Product> getProduct(
            @Parameter(description = "产品信息ID") @PathVariable Long id
        ) {
            Product formData = productService.getProductData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改产品信息")
        @PutMapping(value = "/{id}")
        public Result updateProduct(@Parameter(description = "产品信息ID") @PathVariable Long id,
        @RequestBody @Validated Product formData) {
            boolean result = productService.updateProduct(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除产品信息")
        @DeleteMapping("/{ids}")
        public Result deleteProducts(
            @Parameter(description = "产品信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = productService.deleteProducts(ids);
            return Result.judge(result);
        }
}

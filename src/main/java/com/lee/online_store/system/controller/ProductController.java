package com.lee.online_store.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.online_store.common.result.Result;
import com.lee.online_store.system.model.dto.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.online_store.system.model.entity.Product;
import com.lee.online_store.system.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 商品信息 前端控制器
 *
 * @author baomidou
 * @since 2024-11-26
 */
@Tag(name = "商品信息接口")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

        private final ProductService productService;

        @Operation(summary = "商品信息 分页列表")
        @GetMapping("/page")
        public Result listPagedProducts(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
                                        ProductQuery queryParams ) {
            IPage<Product> result = productService.listPagedProducts(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "商品信息列表")
        @GetMapping("/list")
        public Result listProducts(ProductQuery queryParams ) {
            List<Product> result = productService.listProducts(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增商品信息")
        @PostMapping
        public Result saveProduct(@RequestBody @Valid Product formData ) {
            boolean result = productService.saveProduct(formData);
            return Result.judge(result);
        }

        @Operation(summary = "商品信息表单数据")
        @GetMapping("/{id}/form")
        public Result<Product> getProduct(
            @Parameter(description = "商品信息ID") @PathVariable Long id
        ) {
            Product formData = productService.getProductData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改商品信息")
        @PutMapping(value = "/{id}")
        public Result updateProduct(@Parameter(description = "商品信息ID") @PathVariable Long id,
        @RequestBody @Validated Product formData) {
            boolean result = productService.updateProduct(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除商品信息")
        @DeleteMapping("/{ids}")
        public Result deleteProducts(
            @Parameter(description = "商品信息ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = productService.deleteProducts(ids);
            return Result.judge(result);
        }
}

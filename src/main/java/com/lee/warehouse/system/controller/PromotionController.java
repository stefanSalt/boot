package com.lee.warehouse.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.warehouse.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.warehouse.system.model.entity.Promotion;
import com.lee.warehouse.system.service.PromotionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 促销活动 前端控制器
 *
 * @author baomidou
 * @since 2024-11-27
 */
@Tag(name = "促销活动接口")
@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController {

        private final PromotionService promotionService;

        @Operation(summary = "促销活动 分页列表")
        @GetMapping("/page")
        public Result listPagedPromotions(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Promotion queryParams ) {
            IPage<Promotion> result = promotionService.listPagedPromotions(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增促销活动")
        @PostMapping
        public Result savePromotion(@RequestBody @Valid Promotion formData ) {
            boolean result = promotionService.savePromotion(formData);
            return Result.judge(result);
        }

        @Operation(summary = "促销活动表单数据")
        @GetMapping("/{id}/form")
        public Result<Promotion> getPromotion(
            @Parameter(description = "促销活动ID") @PathVariable Long id
        ) {
            Promotion formData = promotionService.getPromotionData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改促销活动")
        @PutMapping(value = "/{id}")
        public Result updatePromotion(@Parameter(description = "促销活动ID") @PathVariable Long id,
        @RequestBody @Validated Promotion formData) {
            boolean result = promotionService.updatePromotion(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除促销活动")
        @DeleteMapping("/{ids}")
        public Result deletePromotions(
            @Parameter(description = "促销活动ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = promotionService.deletePromotions(ids);
            return Result.judge(result);
        }
}

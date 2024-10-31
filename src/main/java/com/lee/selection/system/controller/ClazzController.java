package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.ClazzForm;
import com.lee.selection.system.model.query.ClazzPageQuery;
import com.lee.selection.system.model.vo.ClazzPageVO;
import com.lee.selection.system.service.ClazzService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


/**
 * 班级 前端控制器
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Tag(name = "班级接口")
@RestController
@RequestMapping("/api/v1/clazzes")
@RequiredArgsConstructor
public class ClazzController {

        private final ClazzService clazzService;

        @Operation(summary = "班级 列表")
        @GetMapping("/list")
        public Result<List<ClazzPageVO>> listPagedClazzes(ClazzPageQuery queryParams ) {
            List<ClazzPageVO> result = clazzService.listClazzs(queryParams);
            return Result.success(result);
        }

        @Operation(summary = "新增班级")
        @PostMapping
        public Result saveClazz(@RequestBody @Valid ClazzForm formData ) {
            boolean result = clazzService.saveClazz(formData);
            return Result.judge(result);
        }

        @Operation(summary = "班级表单数据")
        @GetMapping("/{id}/form")
        public Result<ClazzForm> getClazzForm(
            @Parameter(description = "班级ID") @PathVariable Long id
        ) {
            ClazzForm formData = clazzService.getClazzFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改班级")
        @PutMapping(value = "/{id}")
        public Result updateClazz(@Parameter(description = "班级ID") @PathVariable Long id,
        @RequestBody @Validated ClazzForm formData) {
            boolean result = clazzService.updateClazz(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除班级")
        @DeleteMapping("/{ids}")
        public Result deleteClazzs(
            @Parameter(description = "班级ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = clazzService.deleteClazzs(ids);
            return Result.judge(result);
        }
}

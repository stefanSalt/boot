package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Company;
import com.lee.selection.system.service.CompanyService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 企业 前端控制器
 *
 * @author baomidou
 * @since 2024-11-12
 */
@Tag(name = "企业接口")
@RestController
@RequestMapping("/api/v1/companys")
@RequiredArgsConstructor
public class CompanyController {

        private final CompanyService companyService;

        @Operation(summary = "企业 分页列表")
        @GetMapping("/page")
        public Result listPagedCompanys(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Company queryParams ) {
            IPage<Company> result = companyService.listPagedCompanys(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @GetMapping("/options")
        public Result listOption() {
            return Result.success(companyService.listOption());
        }

        @Operation(summary = "新增企业")
        @PostMapping
        public Result saveCompany(@RequestBody @Valid Company formData ) {
            boolean result = companyService.saveCompany(formData);
            return Result.judge(result);
        }

        @Operation(summary = "企业表单数据")
        @GetMapping("/{id}/form")
        public Result<Company> getCompany(
            @Parameter(description = "企业ID") @PathVariable Long id
        ) {
            Company formData = companyService.getCompanyData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改企业")
        @PutMapping(value = "/{id}")
        public Result updateCompany(@Parameter(description = "企业ID") @PathVariable Long id,
        @RequestBody @Validated Company formData) {
            boolean result = companyService.updateCompany(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除企业")
        @DeleteMapping("/{ids}")
        public Result deleteCompanys(
            @Parameter(description = "企业ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = companyService.deleteCompanys(ids);
            return Result.judge(result);
        }
}

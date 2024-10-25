package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.RoleForm;
import com.lee.selection.system.model.query.RolePageQuery;
import com.lee.selection.system.model.vo.RolePageVO;
import com.lee.selection.system.service.RoleService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 角色信息表 前端控制器
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Tag(name = "角色信息表接口")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

        private final RoleService roleService;

        @Operation(summary = "角色信息表 分页列表")
        @GetMapping("/page")
        public PageResult<RolePageVO> listPagedRoles(RolePageQuery queryParams ) {
            IPage<RolePageVO> result = roleService.listPagedRoles(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增角色信息表")
        @PostMapping
        public Result saveRole(@RequestBody @Valid RoleForm formData ) {
            boolean result = roleService.saveRole(formData);
            return Result.judge(result);
        }

        @Operation(summary = "角色信息表表单数据")
        @GetMapping("/{id}/form")
        public Result<RoleForm> getRoleForm(
            @Parameter(description = "角色信息表ID") @PathVariable Long id
        ) {
            RoleForm formData = roleService.getRoleFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改角色信息表")
        @PutMapping(value = "/{id}")
        public Result updateRole(@Parameter(description = "角色信息表ID") @PathVariable Long id,
        @RequestBody @Validated RoleForm formData) {
            boolean result = roleService.updateRole(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除角色信息表")
        @DeleteMapping("/{ids}")
        public Result deleteRoles(
            @Parameter(description = "角色信息表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = roleService.deleteRoles(ids);
            return Result.judge(result);
        }
}

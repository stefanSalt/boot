package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.service.RoleService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 角色 前端控制器
 *
 * @author baomidou
 * @since 2024-11-07
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

        private final RoleService roleService;

        @Operation(summary = "角色 分页列表")
        @GetMapping("/page")
        public Result listPagedRoles(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10")Integer pageSize,
        Role queryParams ) {
            IPage<Role> result = roleService.listPagedRoles(queryParams,pageNum,pageSize);
            return Result.success(result);
        }

        @Operation(summary = "新增角色")
        @PostMapping
        public Result saveRole(@RequestBody @Valid Role formData ) {
            boolean result = roleService.saveRole(formData);
            return Result.judge(result);
        }

        @Operation(summary = "角色表单数据")
        @GetMapping("/{id}/form")
        public Result<Role> getRole(
            @Parameter(description = "角色ID") @PathVariable Long id
        ) {
            Role formData = roleService.getRoleData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改角色")
        @PutMapping(value = "/{id}")
        public Result updateRole(@Parameter(description = "角色ID") @PathVariable Long id,
        @RequestBody @Validated Role formData) {
            boolean result = roleService.updateRole(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除角色")
        @DeleteMapping("/{ids}")
        public Result deleteRoles(
            @Parameter(description = "角色ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = roleService.deleteRoles(ids);
            return Result.judge(result);
        }
}

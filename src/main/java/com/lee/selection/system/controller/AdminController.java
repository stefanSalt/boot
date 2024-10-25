package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.AdminForm;
import com.lee.selection.system.model.form.PasswordChangeForm;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.query.AdminPageQuery;
import com.lee.selection.system.model.vo.AdminPageVO;
import com.lee.selection.system.model.vo.AdminVO;
import com.lee.selection.system.model.vo.ProfileVO;
import com.lee.selection.system.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 管理员 前端控制器
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Tag(name = "管理员接口")
@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

        private final AdminService adminService;

        @Operation(summary = "管理员 分页列表")
        @GetMapping("/page")
        public PageResult<AdminPageVO> listPagedAdmins(AdminPageQuery queryParams ) {
            IPage<AdminPageVO> result = adminService.listPagedAdmins(queryParams);
            return PageResult.success(result);
        }

        @GetMapping("/me")
        public Result<AdminVO> getAdminInfo() {
            AdminVO currentAdminInfo = adminService.getCurrentAdminInfo();
            return Result.success(currentAdminInfo);
        }

    @Operation(summary = "获取个人中心用户信息")
    @GetMapping("/profile")
    public Result<ProfileVO> getUserProfile() {
        ProfileVO userProfile = adminService.getProfile();

        return Result.success(userProfile);
    }

    @Operation(summary = "修改个人中心用户信息")
    @PutMapping("/profile")
    public Result<?> updateUserProfile(@RequestBody ProfileForm formData) {
        boolean result = adminService.updateProfile(formData);
        return Result.judge(result);
    }

    @Operation(summary = "重置用户密码")
    @PutMapping(value = "/{userId}/password/reset")
    public Result<?> resetPassword(
            @Parameter(description = "用户ID") @PathVariable Integer userId,
            @RequestParam String password
    ) {
        boolean result = adminService.resetPassword(userId, password);
        return Result.judge(result);
    }

    @Operation(summary = "修改密码")
    @PutMapping(value = "/password")
    public Result<?> changePassword(
            @RequestBody PasswordChangeForm data
    ) {
        boolean result = adminService.changePassword( data);
        return Result.judge(result);
    }


        @Operation(summary = "新增管理员")
        @PostMapping
        public Result saveAdmin(@RequestBody @Valid AdminForm formData ) {
            boolean result = adminService.saveAdmin(formData);
            return Result.judge(result);
        }

        @Operation(summary = "管理员表单数据")
        @GetMapping("/{id}/form")
        public Result<AdminForm> getAdminForm(
            @Parameter(description = "管理员ID") @PathVariable Long id
        ) {
            AdminForm formData = adminService.getAdminFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改管理员")
        @PutMapping(value = "/{id}")
        public Result updateAdmin(@Parameter(description = "管理员ID") @PathVariable Long id,
        @RequestBody @Validated AdminForm formData) {
            boolean result = adminService.updateAdmin(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除管理员")
        @DeleteMapping("/{ids}")
        public Result deleteAdmins(
            @Parameter(description = "管理员ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = adminService.deleteAdmins(ids);
            return Result.judge(result);
        }
}

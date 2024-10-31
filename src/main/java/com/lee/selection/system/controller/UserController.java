package com.lee.selection.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.common.result.PageResult;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.vo.ProfileVO;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.selection.system.model.form.UserForm;
import com.lee.selection.system.model.query.UserPageQuery;
import com.lee.selection.system.model.vo.UserPageVO;
import com.lee.selection.system.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 用户 前端控制器
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @Operation(summary = "用户 分页列表")
        @GetMapping("/page")
        public PageResult<UserPageVO> listPagedUsers(UserPageQuery queryParams ) {
            IPage<UserPageVO> result = userService.listPagedUsers(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "用户option列表")
        @GetMapping("/options/{roleId}")
        public Result listUsers(@PathVariable Integer roleId) {
            return Result.success(userService.getOptions(roleId));
        }

    @GetMapping("/me")
    public Result<UserVO> getAdminInfo() {
        UserVO currentAdminInfo = userService.getCurrentUserInfo();
        return Result.success(currentAdminInfo);
    }

    @Operation(summary = "获取个人中心用户信息")
    @GetMapping("/profile")
    public Result<UserProfileVO> getUserProfile() {
        UserProfileVO userProfile = userService.getProfile();

        return Result.success(userProfile);
    }

    @Operation(summary = "修改个人中心用户信息")
    @PutMapping("/profile")
    public Result<?> updateUserProfile(@RequestBody ProfileForm formData) {
        boolean result = userService.updateProfile(formData);
        return Result.judge(result);
    }

        @Operation(summary = "新增用户")
        @PostMapping
        public Result saveUser(@RequestBody @Valid UserForm formData ) {
            boolean result = userService.saveUser(formData);
            return Result.judge(result);
        }

        @Operation(summary = "用户表单数据")
        @GetMapping("/{id}/form")
        public Result<UserForm> getUserForm(
            @Parameter(description = "用户ID") @PathVariable Long id
        ) {
            UserForm formData = userService.getUserFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改用户")
        @PutMapping(value = "/{id}")
        public Result updateUser(@Parameter(description = "用户ID") @PathVariable Long id,
        @RequestBody @Validated UserForm formData) {
            boolean result = userService.updateUser(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除用户")
        @DeleteMapping("/{ids}")
        public Result deleteUsers(
            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = userService.deleteUsers(ids);
            return Result.judge(result);
        }
}

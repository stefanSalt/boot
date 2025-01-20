package com.lee.lecture.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lee.lecture.common.result.Result;
import com.lee.lecture.common.token.service.TokenService;
import com.lee.lecture.system.model.dto.ChangePasswordDTO;
import com.lee.lecture.system.model.dto.UserDTO;
import com.lee.lecture.system.model.entity.User;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lee.lecture.system.service.UserService;
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

        private final TokenService tokenService;

        @Operation(summary = "用户 分页列表")
        @GetMapping("/page")
        public Result listPagedUsers(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 User queryParams ) {
            IPage result = userService.listPagedUsers(queryParams, pageNum, pageSize);
            return Result.success(result);
        }

        @Operation(summary = "用户列表option")
        @GetMapping("/options")
        public Result listUserOptions(
                @RequestParam String role
        ) {
            return Result.success(userService.listUserOptions(role));
        }


    @GetMapping("/me")
    public Result getUserInfo(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.replace("Bearer ", "");
        String username = tokenService.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        return Result.success(user);
    }
    @Operation(summary = "重置用户密码")
    @PutMapping(value = "/{userId}/password/reset")
    public Result<?> resetPassword(
            @Parameter(description = "用户ID") @PathVariable Integer userId,
            @RequestParam String password
    ) {
        boolean result = userService.resetPassword(userId, password);
        return Result.judge(result);
    }

    @Operation(summary = "获取个人中心用户信息")
    @GetMapping("/profile")
    public Result getUserProfile(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.replace("Bearer ", "");
        String username = tokenService.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        return Result.success(user);
    }

    @Operation(summary = "修改个人中心用户信息")
    @PutMapping("/profile")
    public Result<?> updateUserProfile(@RequestBody UserDTO formData) {
        boolean result = userService.updateProfile(formData);
        return Result.judge(result);
    }

    //修改密码
    @Operation(summary = "修改密码")
    @PutMapping("/password")
    public Result<?> updatePassword(
            @RequestBody ChangePasswordDTO changePasswordDTO
            ) {
        boolean result = userService.updatePassword(changePasswordDTO);
        return Result.judge(result);
    }

        @Operation(summary = "新增用户")
        @PostMapping
        public Result saveUser(@RequestBody @Valid UserDTO formData ) {
            boolean result = userService.saveUser(formData);
            return Result.judge(result);
        }

        @Operation(summary = "用户表单数据")
        @GetMapping("/{id}/form")
        public Result<User> getUserForm(
            @Parameter(description = "用户ID") @PathVariable Long id
        ) {
            User formData = userService.getUserData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改用户")
        @PutMapping(value = "/{id}")
        public Result updateUser(@Parameter(description = "用户ID") @PathVariable Long id,
        @RequestBody @Validated UserDTO formData) {
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

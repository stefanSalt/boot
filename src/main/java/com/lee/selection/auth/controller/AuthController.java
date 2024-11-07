package com.lee.selection.auth.controller;

import com.lee.selection.auth.service.AuthService;
import com.lee.selection.common.result.Result;
import com.lee.selection.system.model.dto.LoginResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @Date 2024-10-15 13:20
 */
@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam(name = "username") String username,
            @Parameter(description = "密码", example = "123456") @RequestParam(name = "password") String password,
            @Parameter(description = "角色")@RequestParam(name = "roleId") Integer roleId
    ) {
        LoginResult loginResult = authService.login(username, password,roleId );
        return Result.success(loginResult);
    }

    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    public Result<?> logout() {
        authService.logout();
        return Result.success();
    }

    @Operation(summary = "刷新token")
    @GetMapping("/refresh")
    public Result<LoginResult> refreshToken() {
        LoginResult loginResult = authService.refreshToken();
        return Result.success(loginResult);
    }


}
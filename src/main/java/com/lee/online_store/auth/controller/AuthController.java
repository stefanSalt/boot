package com.lee.online_store.auth.controller;

import com.lee.online_store.auth.service.AuthService;
import com.lee.online_store.common.result.Result;
import com.lee.online_store.system.model.dto.LoginResult;
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
            @Parameter(description = "密码", example = "123456") @RequestParam(name = "password") String password
    ) {
        LoginResult loginResult = authService.login(username, password );
        return Result.success(loginResult);
    }

    //注册
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<?> register(
             @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
           @RequestParam(name = "phone")String phone
    ) {
        authService.register(username, password,phone);
        return Result.success();
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
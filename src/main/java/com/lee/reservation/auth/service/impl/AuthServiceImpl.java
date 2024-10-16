package com.lee.reservation.auth.service.impl;


import cn.hutool.core.util.StrUtil;
import com.lee.reservation.auth.service.AuthService;
import com.lee.reservation.common.token.service.TokenService;
import com.lee.reservation.system.model.dto.LoginResult;
import com.lee.reservation.system.service.AdminService;
import com.lee.reservation.system.service.InstructorService;
import com.lee.reservation.system.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务实现类
 *
 * @author haoxr
 * @since 2.4.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final TokenService tokenService;
    private final AdminService adminService;
    private final InstructorService instructorService;
    private final StudentService studentService;


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public LoginResult login(String username, String password, Integer role) {
        switch (role){
            case 1:
                adminService.login(username,password);
                break;
            case 2:
                instructorService.login(username,password);
                break;

            case 3:
                studentService.login(username,password);
                break;
            default:
                throw new RuntimeException("角色不存在");
        }

        String accessToken = tokenService.generateAccessToken(username);
        String refreshToken = tokenService.generateRefreshToken(username);

        // 创建认证令牌对象
        // 返回包含JWT令牌的登录结果
        return LoginResult.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                // 过期时间
                .expires(tokenService.getExpireTime())
                .build();
    }

    /**
     * 注销
     */
    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(authorization)) {
            String accessToken = authorization.replace("Bearer ", "");
            tokenService.removeToken(accessToken);
            String username = tokenService.getUsernameFromToken(accessToken);
            log.info("用户退出登录，用户名:{}", username);
        }
    }

    @Override
    public LoginResult refreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        String refreshToken = authorization.replace("Bearer ", "");
        if (tokenService.isRefreshTokenValid(refreshToken)) {
            String username = tokenService.getUsernameFromToken(refreshToken);
            String accessToken = tokenService.generateAccessToken(username);
            return LoginResult.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expires(tokenService.getExpireTime())
                    .build();
        }
        throw new RuntimeException("refreshToken无效");
    }
}

package com.lee.warehouse.auth.service;


import com.lee.warehouse.system.model.dto.LoginResult;

/**
 * 认证服务接口
 *
 * @author haoxr
 * @since 2.4.0
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String username, String password);

    /**
     * 登出
     */
    void logout();

    /**
     * 刷新token
     *
     * @return 登录结果
     */
    LoginResult refreshToken();

    void register(String username, String password, String phone);
}

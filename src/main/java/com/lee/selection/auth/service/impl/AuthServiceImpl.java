package com.lee.selection.auth.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.selection.auth.service.AuthService;
import com.lee.selection.common.constant.SystemConstant;
import com.lee.selection.common.token.service.TokenService;
import com.lee.selection.system.model.dto.LoginResult;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    private final UserService userService;


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public LoginResult login(String username, String password, Integer role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username)
                .eq("status",1);
        User user = userService.getOne(queryWrapper);
        // String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5 = SystemConstant.DEFAULT_PASSWORD;
        if (user == null|| !user.getPassword().equals(passwordMD5)){
            throw new RuntimeException("用户名或密码错误");
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

    @Override
    public void register(String username, String password, String phone, Integer roleId) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoleId(roleId);
        userService.save(user);
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

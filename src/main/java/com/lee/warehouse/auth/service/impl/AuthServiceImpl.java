package com.lee.warehouse.auth.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.warehouse.auth.service.AuthService;
import com.lee.warehouse.common.constant.SystemConstant;
import com.lee.warehouse.common.token.service.TokenService;
import com.lee.warehouse.system.model.dto.LoginResult;
import com.lee.warehouse.system.model.entity.User;
import com.lee.warehouse.system.service.UserService;
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

    //声明拦截器



    private final UserService userService;


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public LoginResult login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username)
                .eq("status",1);
        User user = userService.getOne(queryWrapper);

        if (user == null|| !user.getPassword().equals(password)){
            throw new RuntimeException("用户名或密码错误");
        }

        String accessToken = tokenService.generateAccessToken(user);
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
    public void register(String username, String password, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole("user");
        user.setAvatar(SystemConstant.DEFAULT_AVATAR);
        user.setStatus(1);
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
            String username = tokenService.getUsernameFromToken(accessToken);
            log.info("用户退出登录，用户名:{}", username);
        }
    }

    @Override
    public LoginResult refreshToken() {
        //todo 刷新token
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        String refreshToken = authorization.replace("Bearer ", "");
        if (tokenService.isRefreshTokenValid(refreshToken)) {
            String username = tokenService.getUsernameFromToken(refreshToken);
            User user = userService.getUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(user);
            return LoginResult.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expires(tokenService.getExpireTime())
                    .build();
        }
        throw new RuntimeException("refreshToken无效");
    }
}

package com.lee.warehouse.auth.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lee.warehouse.common.exception.BusinessException;
import com.lee.warehouse.common.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * token 拦截器
 *
 */
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor{
    private final TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization" );
        if (StringUtils.isBlank(token)){
            throw new BusinessException("token失效");
        }
        token = token.replace("Bearer ", "");
        if (!tokenService.isAccessTokenValid(token)){
            throw new BusinessException("token失效");
        }
        return true;
    }

}
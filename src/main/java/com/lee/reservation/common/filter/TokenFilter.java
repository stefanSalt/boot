package com.lee.reservation.common.filter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lee.reservation.common.exception.BusinessException;
import com.lee.reservation.common.token.model.entity.Token;
import com.lee.reservation.common.token.service.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * token过滤器
 *
 */
public class TokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //放行登陆及静态资源路径
    private static final String[] IGNORE_URI = {"/api/v1/auth/login","/api/v1/auth/captcha","/api/v1/auth/refresh","/doc.html","/swagger-ui.html",
            "/favicon.ico","/swagger-resources/**","/files","/v3/api-docs/**"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        //放行登陆及静态资源路径
        for (String uri : IGNORE_URI) {
            if (request.getRequestURI().startsWith(uri)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if(StrUtil.isBlank(token)){
//            filterChain.doFilter(request, response);
            throw new BusinessException("token为空");
        }
        token = token.replace("Bearer ", "");
        //解析token并验证
        if (StrUtil.isNotBlank(token)) {
            //解析username
            String username = tokenService.getUsernameFromToken(token);
            //用户名为空，抛出异常
            if (StringUtils.isBlank(username)){
                throw new BusinessException("token解析失败");
            }
            Token byToken = tokenService.getToken(token);
            if (byToken == null||byToken.getExpireTime().isBefore(LocalDateTime.now())){
                throw new BusinessException("token过期,请重新登陆");
            }
            request.setAttribute("username", username);
        }
        filterChain.doFilter(request, response);
    }


}
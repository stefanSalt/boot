package com.lee.selection.common.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.common.token.model.entity.Token;
/**
 * token 服务类
 *
 * @author baomidou
 * @since 2024-10-15
 */
public interface TokenService extends IService<Token> {


    Long getExpireTime();

    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    boolean isAccessTokenValid(String token);

    boolean isRefreshTokenValid(String token);

    String getUsernameFromToken(String token);


    Token getToken(String token);

    boolean removeToken(String token);
}

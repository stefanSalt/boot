package com.lee.online_store.common.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.online_store.common.token.model.entity.Token;
import com.lee.online_store.system.model.entity.User;

/**
 * token 服务类
 *
 * @author baomidou
 * @since 2024-10-15
 */
public interface TokenService extends IService<Token> {


    Long getExpireTime();

//    String generateAccessToken(String username);

    String generateAccessToken(User user);

    String generateRefreshToken(String username);

    boolean isAccessTokenValid(String token);

    boolean isRefreshTokenValid(String token);

    String getUsernameFromToken(String token);

    Integer getRoleIdFromToken(String token);

    Integer getUserIdFromToken(String token);



    boolean removeToken(String token);
}

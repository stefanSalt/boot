package com.lee.lecture.common.token.service;


import com.lee.lecture.system.model.entity.User;

/**
 * token 服务类
 *
 * @author baomidou
 * @since 2024-10-15
 */
public interface TokenService  {


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

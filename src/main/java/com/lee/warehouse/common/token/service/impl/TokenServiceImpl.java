package com.lee.warehouse.common.token.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.lee.warehouse.common.token.service.TokenService;
import com.lee.warehouse.system.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * token服务实现类
 *
 * @author baomidou
 * @since 2024-10-15
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl  implements TokenService {

    private static final String SECRET_KEY_ACCESS = "your-access-token-secret-key";
    private static final String SECRET_KEY_REFRESH = "your-refresh-token-secret-key";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 360000000L; // Access token expires in 1 hour
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 2592000000L; // Refresh token expires in 30 days



    @Override
    public Long getExpireTime(){
        return ACCESS_TOKEN_EXPIRATION_TIME;
    }



    @Override
    public String generateAccessToken(User user) {
        String token = JWT.create().withClaim("username", user.getUsername())
                .withClaim("userId", user.getId())
                .withClaim("role", user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY_ACCESS));
        return token;
    }



    @Override
    public String generateRefreshToken(String username) {
        String token = JWT.create().withClaim("username", username)
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY_REFRESH));
        return token;
    }

    @Override
    public boolean isAccessTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY_ACCESS)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        try{
            JWT.require(Algorithm.HMAC512(SECRET_KEY_REFRESH)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        String username = "";
        try {
            Claim usernameClaim = JWT.decode(token).getClaim("username");
            username=usernameClaim.asString();
        } catch (Exception e) {
            username = null;
            log.info("error:{}", "用户名未能获取 from token");
        }
        return username;
    }

    @Override
    public Integer getRoleIdFromToken(String token) {
        Integer roleId = null;
        try {
            Claim roleIdClaim = JWT.decode(token).getClaim("roleId");
            roleId=roleIdClaim.asInt();
        } catch (Exception e) {
            log.info("error:{}", "角色id未能获取 from token");
        }

        return roleId;
    }

    @Override
    public Integer getUserIdFromToken(String token) {
        Integer userId = null;
        try {
            Claim userIdClaim = JWT.decode(token).getClaim("userId");
            userId=userIdClaim.asInt();
        } catch (Exception e) {
            log.info("error:{}", "用户id未能获取 from token");
        }
        return userId;
    }



    @Override
    public boolean removeToken(String token){
        return true;
    }
    

}

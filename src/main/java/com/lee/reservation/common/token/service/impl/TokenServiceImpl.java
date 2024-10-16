package com.lee.reservation.common.token.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.reservation.common.token.model.entity.Token;
import com.lee.reservation.common.token.mapper.TokenMapper;
import com.lee.reservation.common.token.service.TokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    private static final String SECRET_KEY_ACCESS = "your-access-token-secret-key";
    private static final String SECRET_KEY_REFRESH = "your-refresh-token-secret-key";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 3600000L; // Access token expires in 1 hour
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 2592000000L; // Refresh token expires in 30 days



    @Override
    public Long getExpireTime(){
        return ACCESS_TOKEN_EXPIRATION_TIME;
    }

    @Override
    public String generateAccessToken(String username) {

      String token = JWT.create().withClaim("username", username)
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY_ACCESS));
      saveAccessToken(token);
        return token;
    }

    public void saveAccessToken(String token){
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setTokenType(0);
        tokenEntity.setExpireTime(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRATION_TIME));
        save(tokenEntity);
    }
    public void saveRefreshToken(String token){
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setTokenType(1);
        tokenEntity.setExpireTime(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRATION_TIME));
        save(tokenEntity);
    }

    @Override
    public String generateRefreshToken(String username) {
        String token = JWT.create().withClaim("username", username)
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY_REFRESH));
        saveRefreshToken(token);
        return token;
    }

    @Override
    public boolean isAccessTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY_REFRESH)).build().verify(token);
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
    public Token getToken(String token){
        QueryWrapper<Token> tokenQueryWrapper = new QueryWrapper<>();
        tokenQueryWrapper.eq("token",token);
        return getOne(tokenQueryWrapper);
    }

    @Override
    public boolean removeToken(String token){
        return remove(new QueryWrapper<Token>().eq("token",token));
    }
    

}

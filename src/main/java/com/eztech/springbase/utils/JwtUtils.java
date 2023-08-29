package com.eztech.springbase.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.eztech.springbase.entity.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author chenqinru
 * @date 2023/07/29
 */
public final class JwtUtils {
    public static final String SECRET = "eztech";

    private JwtUtils() {
    }

    public static String create(User user) {
        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("nickname", user.getNickname())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return false;
        }
    }

    public static Map<String, Claim> decode(String token) {
        Map<String, Claim> claims = JwtContextUtils.getClaims();
        if (claims.isEmpty()) {
            claims = JWT.decode(token).getClaims();
            JwtContextUtils.setClaims(claims);
        }

        return claims;
    }

    public static boolean isExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public static Long getUserId(String token) {
        return decode(token).getOrDefault("userId", null).asLong();
    }

    public static String getUsername(String token) {
        return decode(token).getOrDefault("username",null).asString();
    }

    public static String getNickname(String token) {
        return decode(token).getOrDefault("nickname",null).asString();
    }

}

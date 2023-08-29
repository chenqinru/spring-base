package com.eztech.springbase.utils;

import com.auth0.jwt.interfaces.Claim;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt上下文工具类
 *
 * @author chenqinru
 * @date 2023/08/29
 */
public class JwtContextUtils {
    private static final ThreadLocal<Map<String, Claim>> JWT_CLAIMS = ThreadLocal.withInitial(HashMap::new);


    public static void setClaims(Map<String, Claim> claims) {
        JWT_CLAIMS.set(claims);
    }

    public static Map<String, Claim> getClaims() {
        return JWT_CLAIMS.get();
    }

    public static void clear() {
        JWT_CLAIMS.remove();
    }
}

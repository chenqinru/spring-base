package com.eztech.springbase.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销服务
 *
 * @author chenqinru
 * @date 2023/07/19
 */
@Service
public class LogoutServiceImpl implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //final String authHeader = request.getHeader("Authorization");
        //final String jwt;
        //if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //    return;
        //}
        //jwt = authHeader.substring(7);
        //var storedToken = tokenRepository.findByToken(jwt)
        //        .orElse(null);
        //if (storedToken != null) {
        //    storedToken.setExpired(true);
        //    storedToken.setRevoked(true);
        //    tokenRepository.save(storedToken);
        //    SecurityContextHolder.clearContext();
        //}
        SecurityContextHolder.clearContext();
    }
}

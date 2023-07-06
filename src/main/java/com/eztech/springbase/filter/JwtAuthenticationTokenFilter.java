package com.eztech.springbase.filter;

import com.eztech.springbase.config.SecurityIgnoreUrl;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.utils.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * token验证过滤器
 *
 * @author chenqinru
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    @Override
    //@Transactional
    public void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        Stream<RequestMatcher> matchers = Arrays.stream(securityIgnoreUrl.getUrls()).map(AntPathRequestMatcher::new);
        if (matchers.anyMatch(matcher -> matcher.matches(request))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token == null) {
            throw new CustomException(ResultEnum.TOKEN_EMPTY);
        }

        if (!JwtUtil.verify(token)) {
            throw new CustomException(ResultEnum.TOKEN_INVALID);
        }

        if (JwtUtil.isExpired(token)) {
            throw new CustomException(ResultEnum.TOKEN_EXPIRED);
        }

        String username = JwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}

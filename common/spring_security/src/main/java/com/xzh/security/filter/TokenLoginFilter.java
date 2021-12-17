package com.xzh.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzh.security.entity.SecurityUser;
import com.xzh.security.entity.SysUser;
import com.xzh.security.security.TokenManager;
import com.xzh.utils.utils.R;
import com.xzh.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 认证过滤器
 *
 * @author XZH
 * @date 2021年12月14日 14:00
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager,
                            RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    /**
     * 获取提交用户名密码
     *
     * @author XZH
     * @date 2021/12/14 14:03
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            SysUser user = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),
                    user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 认证成功操作
     *
     * @author XZH
     * @date 2021/12/14 14:05
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String token = tokenManager.createToken(user.getUserInfo().getUserName());
        redisTemplate.opsForValue().set(user.getUserInfo().getUserName(), user.getPermissionValueList());
        ResponseUtil.out(response, R.ok().data("token", token));
    }

    /**
     * 认证失败操作
     *
     * @author XZH
     * @date 2021/12/14 14:06
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}

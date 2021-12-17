package com.xzh.security.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xzh.utils.utils.R;
import com.xzh.utils.utils.ResponseUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XZH
 * @date 2021年12月14日 13:41
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
    }

    /**
     * Redis中删除用户信息
     *
     * @author XZH
     * @date 2021/12/14 13:51
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        if (!StringUtils.isBlank(token)) {
            String username = tokenManager.getUserInfoFromToken(token);
            redisTemplate.delete(username);
        }
        ResponseUtil.out(response, R.ok());

    }

}

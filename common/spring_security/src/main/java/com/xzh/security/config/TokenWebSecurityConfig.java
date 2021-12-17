package com.xzh.security.config;

import com.xzh.security.filter.TokenAuthenticationFilter;
import com.xzh.security.filter.TokenLoginFilter;
import com.xzh.security.security.DefaultPasswordEncoder;
import com.xzh.security.security.TokenLogoutHandler;
import com.xzh.security.security.TokenManager;
import com.xzh.security.security.UnanthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author XZH
 * @date 2021年12月14日 14:50
 */
@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private DefaultPasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public TokenWebSecurityConfig(TokenManager tokenManager, RedisTemplate redisTemplate,
                                  DefaultPasswordEncoder passwordEncoder,
                                  UserDetailsService userDetailsService) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                //未授权处理
                .authenticationEntryPoint(new UnanthEntryPoint())
                //关闭csrf防护
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                //退出路径
                .and().logout().logoutUrl("/admin/acl/index/logout")
                //退出处理
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate))
                //认证过滤器
                .and().addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                //授权过滤器
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
                .httpBasic();
    }

    /**
     * 用户名密码处理
     *
     * @author XZH
     * @date 2021/12/14 15:04
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 放行路径
     *
     * @author XZH
     * @date 2021/12/14 15:04
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/*");
    }

}

package com.xzh.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author XZH
 * @date 2021年12月14日 13:41
 */
public class TokenManager {

    /**
     * token有效时间
     *
     * @author XZH
     * @date 2021/12/14 13:47
     */
    public long tokenExpiration = 24 * 60 * 60 * 1000;

    private final String tokenSignKey = "123456";

    /**
     * @author XZH
     * @date 2021/12/14 13:50
     * 根据用户名生成Token
     */
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * @author XZH
     * @date 2021/12/14 13:49
     * 根据token字符串获得用户信息
     */
    public String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }
}
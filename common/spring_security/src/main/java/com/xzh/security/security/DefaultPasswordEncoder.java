package com.xzh.security.security;

import com.xzh.utils.utils.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author XZH
 * @date 2021年12月14日 13:35
 * 密码处理类
 */
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {

    }

    /**
     * 密码MD5加密
     *
     * @author XZH
     * @date 2021/12/14 13:40
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return Md5Utils.hash(rawPassword.toString());
    }

    /**
     * 密码对比
     *
     * @author XZH
     * @date 2021/12/14 13:40
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(Md5Utils.hash(rawPassword.toString()));
    }
}

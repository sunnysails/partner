package com.kaishengit.security;

import com.kaishengit.dto.MyUserDetails;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by sunny on 2017/3/24.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Value("${password.salt}")
    private String salt;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Object details = authentication.getDetails();//内含当前登录IP sessionId
        String ip = ((WebAuthenticationDetails) details).getRemoteAddress();
        password = DigestUtils.md5Hex(password + salt);
        MyUserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("用户未找到");
        }
        //加密过程在这里体现
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        userDetailsService.addLog(ip,user.getId());
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

package com.kaishengit.shiro;


import com.kaishengit.pojo.User;
import com.kaishengit.service.RoleService;
import com.kaishengit.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by sunny on 2017/2/18.
 */
@Component
public class ShrioDbRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //返回当前登录的对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取当前对象拥有的角色
        String roleName = user.getRole().getRoleName();
        if (roleName.isEmpty()) {
            return null;
        } else {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRole(roleName);
            return authorizationInfo;
        }
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        User user = userService.findByUserName(userName);
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;
    }
}

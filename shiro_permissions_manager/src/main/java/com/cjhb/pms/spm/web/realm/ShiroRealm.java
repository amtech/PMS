package com.cjhb.pms.spm.web.realm;

import com.cjhb.pms.domain.spm_pojo.User;
import com.cjhb.pms.service.spm.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm授权与验证实现
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService usv;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        // 授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(usv.getRoles(username));
        authorizationInfo.setStringPermissions(usv.getPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = usv.find(username);
        if (user == null) {
            throw new UnknownAccountException("未知用户");
        }
        // 认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        return authenticationInfo;
    }

}

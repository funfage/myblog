package com.zrf.myblog.realm;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.exception.BlogException;
import com.zrf.myblog.pojo.Admin;
import com.zrf.myblog.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理管理员的登录和授权逻辑
 * @author 张润发
 * @date 2020/6/5
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证(登录)
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Admin admin = adminService.getByUsername(username);
        if (admin == null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在");
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
    }

}

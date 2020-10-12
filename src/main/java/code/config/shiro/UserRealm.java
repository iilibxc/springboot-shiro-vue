package code.config.shiro;

import code.common.Constant;
import code.mapper.LoginMapper;
import code.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @description: 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    private LoginMapper loginMapper;

    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        SysUser permission = (SysUser) session.getAttribute(Constant.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        logger.info("本用户权限为:" + permission.getPermissionList());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.getPermissionList());
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());
        SysUser user = loginMapper.getUser(loginName, password);
        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //session中不需要保存密码
        user.setPassword("");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_USER_INFO, user);
        return authenticationInfo;
    }
}

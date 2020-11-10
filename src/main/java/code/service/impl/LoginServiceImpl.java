package code.service.impl;

import code.common.Constant;
import code.common.ResponseCode;
import code.common.ServerResponse;
import code.mapper.LoginMapper;
import code.mapper.SysPermissionMapper;
import code.pojo.SysUser;
import code.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 登录表单提交
     */
    @Override
    public ServerResponse authLogin(SysUser sysUser) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        try {
            currentUser.login(token);
            return ServerResponse.success("success");
        } catch (AuthenticationException e) {
            return ServerResponse.error(ResponseCode.AUTHENTICATION_EXCEPTION);
        }
    }

    /**
     * 根据用户名和密码查询对应的用户
     */
    @Override
    public ServerResponse getUser(String username, String password) {
        SysUser sysUser = loginMapper.getUser(username, password);
        if (sysUser != null) {
            return ServerResponse.success(sysUser);
        } else {
            return ServerResponse.error(ResponseCode.USER_NOT_EXISTS);
        }
    }

    /**
     * 查询当前登录用户的权限等信息
     */
    @Override
    public ServerResponse getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        SysUser userInfo = (SysUser) session.getAttribute(Constant.SESSION_USER_INFO);
        if (userInfo == null) {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return ServerResponse.success(ResponseCode.LOGIN_EXPIRED);
        } else {
            String username = userInfo.getUsername();
            SysUser userPermission = sysPermissionMapper.getUserPermission(username);
            session.setAttribute(Constant.SESSION_USER_PERMISSION, userPermission);
            if (userPermission != null) {
                return ServerResponse.success(userPermission);
            } else {
                return ServerResponse.error(ResponseCode.INSUFFICIENT_AUTHORITY);
            }
        }

    }

    /**
     * 退出登录
     */
    @Override
    public ServerResponse logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return ServerResponse.success("success");
        } catch (Exception e) {
            return ServerResponse.error("fail");
        }
    }
}
// 341204194104261218
// 341204194209211225
// 341204196606051228
// 341204196807031211
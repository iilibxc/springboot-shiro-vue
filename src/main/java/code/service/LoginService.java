package code.service;

import code.common.ServerResponse;
import code.pojo.SysUser;


public interface LoginService {
    /**
     * 登录表单提交
     */
    ServerResponse authLogin(SysUser user);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     */
    ServerResponse getUser(String username, String password);

    /**
     * 查询当前登录用户的权限等信息
     */
    ServerResponse getInfo();

    /**
     * 退出登录
     */
    ServerResponse logout();
}

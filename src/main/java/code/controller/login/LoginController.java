package code.controller.login;

import code.common.ServerResponse;
import code.pojo.SysUser;
import code.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 登录相关Controller
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/auth")
    public ServerResponse authLogin(@RequestBody SysUser sysUser) {
        return loginService.authLogin(sysUser);

    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/getInfo")
    public ServerResponse getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public ServerResponse logout() {
        return loginService.logout();
    }
}

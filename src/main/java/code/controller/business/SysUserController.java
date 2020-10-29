package code.controller.business;

import code.common.ServerResponse;
import code.entity.Info;
import code.pojo.SysUser;
import code.service.SysUserService;
import code.config.redis.RedisUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 用户/角色/权限相关controller
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public ServerResponse listUser(SysUser sysUser) {
        return sysUserService.listUser(sysUser);
    }

    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public ServerResponse addUser(@RequestBody SysUser sysUser) {
        return sysUserService.addUser(sysUser);
    }

    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public ServerResponse updateUser(@RequestBody SysUser sysUser) {
        return sysUserService.updateUser(sysUser);
    }

    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public ServerResponse getAllRoles() {
        return sysUserService.getAllRoles();
    }

    private static int ExpireTime = 60*60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value) {
        key="info";
        Info info = new Info();
        info.setId("t-000001");
        info.setName("Name-1");
        info.setFullName("Name-1-李");
        info.setComment("C=测试");
        info.setMail("Name-1@test.com");
        info.setAddress("address");
        info.setRegDate(new Date());
        info.setReg2Date(new Date());
        return redisUtil.set(key, info, ExpireTime);
    }

    @RequestMapping("get")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }


}

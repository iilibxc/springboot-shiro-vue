package code.controller.business;

import code.common.ServerResponse;
import code.pojo.SysRole;
import code.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public ServerResponse listRole(SysRole role) {
        return sysRoleService.listRole(role);
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public ServerResponse listAllPermission() {
        return sysRoleService.listAllPermission();
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public ServerResponse addRole(@RequestBody SysRole requestJson) {
        return sysRoleService.addRole(requestJson);
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public ServerResponse updateRole(@RequestBody SysRole sysRole) {
        return sysRoleService.updateRole(sysRole);
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public ServerResponse deleteRole(@RequestBody SysRole sysRole) {
        return sysRoleService.deleteRole(sysRole);
    }
}

package code.service;

import code.common.ServerResponse;
import code.pojo.SysRole;

public interface SysRoleService {
    /**
     * 角色列表
     */
    ServerResponse listRole();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    ServerResponse listAllPermission();

    /**
     * 添加角色
     */
    ServerResponse addRole(SysRole sysRole);

    /**
     * 修改角色
     */
    ServerResponse updateRole(SysRole sysRole);

    /**
     * 删除角色
     */
    ServerResponse deleteRole(SysRole sysRole);
}

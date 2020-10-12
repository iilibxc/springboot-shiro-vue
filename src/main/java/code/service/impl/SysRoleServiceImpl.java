package code.service.impl;

import code.common.ResponseCode;
import code.common.ServerResponse;
import code.mapper.SysRoleMapper;
import code.mapper.SysRolePermissionMapper;
import code.pojo.SysPermission;
import code.pojo.SysRole;
import code.pojo.SysRolePermission;
import code.pojo.SysUser;
import code.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 角色列表
     */
    @Override
    public ServerResponse listRole() {
        List<SysRole> roles = sysRoleMapper.listRole();
        return ServerResponse.success(roles);
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @Override
    public ServerResponse listAllPermission() {
        List<SysPermission> permissions = sysRoleMapper.listAllPermission();
        return ServerResponse.success(permissions);
    }

    /**
     * 添加角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public ServerResponse addRole(SysRole sysRole) {
        sysRoleMapper.insertRole(sysRole);
        sysRoleMapper.insertRolePermission(sysRole.getId() + "", (List<Integer>) sysRole.getPermissionIds());
        return ServerResponse.success();
    }

    /**
     * 修改角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public ServerResponse updateRole(SysRole sysRole) {
        String roleId = sysRole.getId() + "";
        List<Integer> newPerms = (List<Integer>) sysRole.getPermissionIds();
        SysRole roleInfo = sysRoleMapper.getRoleAllInfo(sysRole);
        Set<Integer> oldPerms = roleInfo.getPermissionIds();
        //修改角色名称
        dealRoleName(sysRole, roleInfo);
        //添加新权限
        saveNewPermission(roleId, newPerms, oldPerms);
        //移除旧的不再拥有的权限
        removeOldPermission(roleId, newPerms, oldPerms);
        return ServerResponse.success();
    }

    /**
     * 修改角色名称
     */
    private void dealRoleName(SysRole paramJson, SysRole roleInfo) {
        String roleName = paramJson.getRoleName();
        if (!roleName.equals(roleInfo.getRoleName())) {
            sysRoleMapper.updateRoleName(paramJson);
        }
    }

    /**
     * 为角色添加新权限
     */
    private void saveNewPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (waitInsert.size() > 0) {
            sysRoleMapper.insertRolePermission(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     */
    private void removeOldPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (waitRemove.size() > 0) {
            sysRolePermissionMapper.removeOldPermission(roleId, waitRemove);
        }
    }

    /**
     * 删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public ServerResponse deleteRole(SysRole sysRole) {
        SysRole roleInfo = sysRoleMapper.getRoleAllInfo(sysRole);
        List<SysUser> users = roleInfo.getUsers();
        if (users != null && users.size() > 0) {
            return ServerResponse.error(ResponseCode.ROLE_STILL_IN_USER);
        }
        sysRoleMapper.removeRole(roleInfo);
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(roleInfo.getId());
        sysRolePermissionMapper.removeRoleAllPermission(sysRolePermission);
        return ServerResponse.success();
    }
}

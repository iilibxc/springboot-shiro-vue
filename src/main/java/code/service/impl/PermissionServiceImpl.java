package code.service.impl;

import code.common.ResponseCode;
import code.common.ServerResponse;
import code.mapper.SysPermissionMapper;
import code.pojo.SysUser;
import code.service.SysPermissionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class PermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    @Override
    public ServerResponse getUserPermission(String username) {
        SysUser sysUser = getUserPermissionFromDB(username);
        if (sysUser != null) {
            return ServerResponse.success(sysUser);
        } else {
            return ServerResponse.error(ResponseCode.INSUFFICIENT_AUTHORITY);
        }
    }

    /**
     * 从数据库查询用户权限信息
     */
    private SysUser getUserPermissionFromDB(String username) {
        SysUser sysUser = sysPermissionMapper.getUserPermission(username);
        //管理员角色ID为1
        int adminRoleId = 1;
        //如果是管理员
        String roleIdKey = "roleId";
        if (adminRoleId == sysUser.getRoleId()) {
            //查询所有菜单  所有权限
            Set<String> menuList = sysPermissionMapper.getAllMenu();
            Set<String> permissionList = sysPermissionMapper.getAllPermission();
            sysUser.setMenuList(menuList);
            sysUser.setPermissionList(permissionList);
        }
        return sysUser;
    }
}

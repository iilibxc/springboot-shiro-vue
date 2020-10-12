package code.mapper;


import code.pojo.SysRole;
import code.pojo.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionMapper {

    /**
     * 删除本角色全部权限
     */
    int removeRoleAllPermission(SysRolePermission sysRolePermission);

    /**
     * 将角色曾经拥有而修改为不再拥有的权限 delete_status改为'2'
     */
    int removeOldPermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);
}

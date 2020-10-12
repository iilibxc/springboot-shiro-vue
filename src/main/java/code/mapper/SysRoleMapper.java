package code.mapper;

import code.pojo.SysPermission;
import code.pojo.SysRole;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    /**
     * 角色列表
     */
    List<SysRole> listRole();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    List<SysPermission> listAllPermission();

    /**
     * 新增角色
     */
    int insertRole(SysRole sysRole);

    /**
     * 批量插入角色的权限
     *
     * @param roleId      角色ID
     * @param permissions 权限
     */
    int insertRolePermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 修改角色名称
     */
    int updateRoleName(SysRole sysRole);

    /**
     * 查询某角色的全部数据
     * 在删除和修改角色时调用
     */
    SysRole getRoleAllInfo(SysRole sysRole);

    /**
     * 删除角色
     */
    int removeRole(SysRole sysRole);


}

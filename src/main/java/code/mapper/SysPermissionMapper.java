package code.mapper;

import code.pojo.SysUser;

import java.util.Set;

public interface SysPermissionMapper {
	/**
	 * 查询用户的角色 菜单 权限
	 */
	SysUser getUserPermission(String username);

	/**
	 * 查询所有的菜单
	 */
	Set<String> getAllMenu();

	/**
	 * 查询所有的权限
	 */
	Set<String> getAllPermission();
}

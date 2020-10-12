package code.service;

import code.common.ServerResponse;
import code.pojo.SysUser;

public interface SysUserService {
	/**
	 * 用户列表
	 */
	ServerResponse listUser(SysUser sysUser);

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	ServerResponse getAllRoles();

	/**
	 * 添加用户
	 */
	ServerResponse addUser(SysUser sysUser);

	/**
	 * 修改用户
	 */
	ServerResponse updateUser(SysUser sysUser);


}

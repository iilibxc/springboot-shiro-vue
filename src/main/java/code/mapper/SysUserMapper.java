package code.mapper;

import code.pojo.SysRole;
import code.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {
	/**
	 * 查询用户数量
	 */
	int countUser( );

	/**
	 * 查询用户列表
	 */
	List<SysUser> listUser(SysUser user);

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	List<SysRole> getAllRoles();

	/**
	 * 校验用户名是否已存在
	 */
	int queryExistUsername(SysUser user);

	/**
	 * 新增用户
	 */
	int addUser(SysUser user);

	/**
	 * 修改用户
	 */
	int updateUser(SysUser user);


}

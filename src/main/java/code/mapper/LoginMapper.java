package code.mapper;

import code.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
public interface LoginMapper {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	SysUser getUser(@Param("username") String username, @Param("password") String password);
}

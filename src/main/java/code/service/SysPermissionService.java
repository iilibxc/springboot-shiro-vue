package code.service;

import code.common.ServerResponse;
import com.alibaba.fastjson.JSONObject;

public interface SysPermissionService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	ServerResponse getUserPermission(String username);
}

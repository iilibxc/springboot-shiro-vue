package code.controller.business;

import code.common.ServerResponse;
import code.pojo.SysUser;
import code.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户/角色/权限相关controller
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("user:list")
	@GetMapping("/list")
	public ServerResponse listUser(SysUser sysUser) {
		return sysUserService.listUser(sysUser);
	}

	@RequiresPermissions("user:add")
	@PostMapping("/addUser")
	public ServerResponse addUser(@RequestBody SysUser sysUser) {
		return sysUserService.addUser(sysUser);
	}

	@RequiresPermissions("user:update")
	@PostMapping("/updateUser")
	public ServerResponse updateUser(@RequestBody SysUser sysUser) {
		return sysUserService.updateUser(sysUser);
	}

	@RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
	@GetMapping("/getAllRoles")
	public ServerResponse getAllRoles() {
		return sysUserService.getAllRoles();
	}


}

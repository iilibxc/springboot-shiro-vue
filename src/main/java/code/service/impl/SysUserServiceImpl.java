package code.service.impl;

import code.common.ResponseCode;
import code.common.ServerResponse;
import code.mapper.SysUserMapper;
import code.pojo.SysRole;
import code.pojo.SysUser;
import code.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 用户列表
     */
    @Override
    public ServerResponse listUser(SysUser sysUser) {

        PageHelper.startPage(sysUser.getPageNum(), sysUser.getPageSize());
        List<SysUser> userPage = sysUserMapper.listUser(sysUser);
        return ServerResponse.success(userPage);
    }

    /**
     * 添加用户
     */
    @Override
    public ServerResponse addUser(SysUser sysUser) {
        int exist = sysUserMapper.queryExistUsername(sysUser);
        if (exist > 0) {
            return ServerResponse.error(ResponseCode.ACCOUNT_ALREADY_EXISTS);
        }
        sysUserMapper.addUser(sysUser);
        return ServerResponse.success();
    }

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     */
    @Override
    public ServerResponse getAllRoles() {
        List<SysRole> roles = sysUserMapper.getAllRoles();
        return ServerResponse.success(roles);
    }

    /**
     * 修改用户
     */
    @Override
    public ServerResponse updateUser(SysUser sysUser) {
        sysUserMapper.updateUser(sysUser);
        return ServerResponse.success();
    }
}

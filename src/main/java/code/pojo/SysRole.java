package code.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
    @Excel(name = "角色名", orderNum = "0")
    @ApiParam(value = "角色名")
    private String roleName;

    @Excel(name = "", orderNum = "1")
    @ApiParam(value = "")
    private Date createTime;

    @Excel(name = "", orderNum = "2")
    @ApiParam(value = "")
    private Date updateTime;

    @Excel(name = "是否有效  1有效  2无效", orderNum = "3")
    @ApiParam(value = "是否有效  1有效  2无效")
    private String deleteStatus;

    @Excel(name = "", orderNum = "4")
    @ApiParam(value = "")
    private Integer id;

    private  String roleId;

    private List<SysUser> users;

    private List<SysPermission> menus;

    private Set<Integer> permissionIds;

}
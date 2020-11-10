package code.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {
    @Excel(name = "用户名", orderNum = "0")
    @ApiParam(value = "用户名")
    private String username;

    @Excel(name = "密码", orderNum = "1")
    @ApiParam(value = "密码")
    private String password;

    @Excel(name = "昵称", orderNum = "2")
    @ApiParam(value = "昵称")
    private String nickname;

    @Excel(name = "角色ID", orderNum = "3")
    @ApiParam(value = "角色ID")
    private Integer roleId;

    @Excel(name = "创建时间", orderNum = "4")
    @ApiParam(value = "创建时间")
    private Date createTime;

    @Excel(name = "修改时间", orderNum = "5")
    @ApiParam(value = "修改时间")
    private Date updateTime;

    @Excel(name = "是否有效  1有效  2无效", orderNum = "6")
    @ApiParam(value = "是否有效  1有效  2无效")
    private String deleteStatus;

    @Excel(name = "", orderNum = "7")
    @ApiParam(value = "")
    private Integer id;

    private String userId;

    private String roleName;

    private Set<String> permissionList;

    private Set<String> menuList;
}
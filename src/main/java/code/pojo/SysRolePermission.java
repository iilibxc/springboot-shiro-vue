package code.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiParam;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限关联
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission {
    @Excel(name = "角色id", orderNum = "0")
    @ApiParam(value = "角色id")
    private Integer roleId;

    @Excel(name = "权限id", orderNum = "1")
    @ApiParam(value = "权限id")
    private Integer permissionId;

    @Excel(name = "", orderNum = "2")
    @ApiParam(value = "")
    private Date createTime;

    @Excel(name = "", orderNum = "3")
    @ApiParam(value = "")
    private Date updateTime;

    @Excel(name = "是否有效 1有效     2无效", orderNum = "4")
    @ApiParam(value = "是否有效 1有效     2无效")
    private String deleteStatus;

    @Excel(name = "", orderNum = "5")
    @ApiParam(value = "")
    private Integer id;

    private String permissionName;
}
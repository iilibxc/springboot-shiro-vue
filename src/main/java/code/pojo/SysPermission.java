package code.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"requiredPermission"})
public class SysPermission extends BaseEntity {
    @Excel(name = "归属菜单,前端判断并展示菜单使用,", orderNum = "0")
    @ApiParam(value = "归属菜单,前端判断并展示菜单使用,")
    private String menuCode;

    @Excel(name = "菜单的中文释义", orderNum = "1")
    @ApiParam(value = "菜单的中文释义")
    private String menuName;

    @Excel(name = "权限的代码/通配符,对应代码中@RequiresPermissions 的value", orderNum = "2")
    @ApiParam(value = "权限的代码/通配符,对应代码中@RequiresPermissions 的value")
    private String permissionCode;

    @Excel(name = "本权限的中文释义", orderNum = "3")
    @ApiParam(value = "本权限的中文释义")
    private String permissionName;

    @Excel(name = "是否本菜单必选权限, 1.必选 2非必选 通常是列表权限是必选", orderNum = "4")
    @ApiParam(value = "是否本菜单必选权限, 1.必选 2非必选 通常是列表权限是必选")
    private Boolean requiredPermission;

    @Excel(name = "自定id,主要供前端展示权限列表分类排序使用.", orderNum = "5")
    @ApiParam(value = "自定id,主要供前端展示权限列表分类排序使用.")
    private Integer id;

    private List<SysPermission> permissions;

}
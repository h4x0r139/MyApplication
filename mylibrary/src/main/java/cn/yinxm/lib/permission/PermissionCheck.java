package cn.yinxm.lib.permission;

/**
 * Created by yinxm on 2016/9/27.
 */

public class PermissionCheck {
    public PermissionCheck(){}
    public PermissionCheck(String permission) {
        this.permission = permission;
    }
    public PermissionCheck(String permission, PermissionGrantResult permissionGrantResult) {
        this.permission = permission;
        this.permissionGrantResult = permissionGrantResult;
    }
    public String permission;
    public PermissionGrantResult permissionGrantResult;
}

package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/9/27.
 * 授权成功、失败回调
 */
public interface PermissionGrant2Result extends PermissionGrantResult {
    public void permissionDenied(String permission);
}

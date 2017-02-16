package cn.yinxm.permission_req;

/**
 * Created by yinxm on 2016/9/27.
 * 成功授权回调
 */
public interface PermissionGrantResult {
    public void permissionGranted(int resultCode);
}

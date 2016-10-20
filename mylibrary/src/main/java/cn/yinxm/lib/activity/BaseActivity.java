package cn.yinxm.lib.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yinxm.lib.permission.PermissionCheck;
import cn.yinxm.lib.permission.PermissionConstant;
import cn.yinxm.lib.permission.PermissionGrant2Result;
import cn.yinxm.lib.permission.PermissionGrantResult;
import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.StringUtil;

public class BaseActivity extends AppCompatActivity {

    Map<String, PermissionGrantResult> permissionGrantResultMap = new HashMap<String, PermissionGrantResult>();

    public void checkDangerPermissions(PermissionCheck permissionCheck) {
        if (permissionCheck != null && StringUtil.isNotBlank(permissionCheck.permission)) {
            List<PermissionCheck> permissionCheckList = new ArrayList<PermissionCheck>(1);
            permissionCheckList.add(permissionCheck);
            checkDangerPermissions(permissionCheckList);
        }
    }

    public void checkDangerPermissions(List<PermissionCheck> permissionCheckList) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (permissionCheckList != null && permissionCheckList.size() > 0) {
                permissionGrantResultMap.clear();
                List<String> permissions = new ArrayList<>();
                for (int i = 0; i < permissionCheckList.size(); i++) {
                    PermissionCheck permissionCheck = permissionCheckList.get(i);
                    if (permissionCheck != null && StringUtil.isNotBlank(permissionCheck.permission)
                            && PermissionConstant.DANGER_PERMISSIONS_NAME_MAP.containsKey(permissionCheck.permission)) {//判断permission合法性，不为空，danger
                        String permission = permissionCheckList.get(i).permission;
                        if (permissionCheckList.get(i).permissionGrantResult != null) {
                            permissionGrantResultMap.put(permission, permissionCheckList.get(i).permissionGrantResult);
                        }
                        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                            //未授权，加入授权队列
                            permissions.add(permission);
                        } else {
                            //已授权的，直接执行回调
                            LogUtil.d("permission " + permission + " is already granted ");
                            if (permissionCheckList.get(i).permissionGrantResult != null) {
                                permissionCheckList.get(i).permissionGrantResult.permissionGranted(permission);
                            }
                        }
                    }
                }
                if (permissions.size() > 0) {
                    String[] missedPermissions = permissions.toArray(new String[permissions.size()]);
                    ActivityCompat.requestPermissions(this, missedPermissions, 0);//Visit https://developer.android.com/training/permissions/requesting.html for more information.
                }
            }
        } else if (permissionCheckList != null && permissionCheckList.size() > 0) {
            //小于23的版本，直接执行授权
            for (PermissionCheck permissionCheck : permissionCheckList) {
                if (permissionCheck != null && StringUtil.isNotBlank(permissionCheck.permission)
                        && permissionCheck.permissionGrantResult != null) {
                    permissionCheck.permissionGrantResult.permissionGranted(permissionCheck.permission);
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.d("onRequestPermissionsResult permissions=" + Arrays.toString(permissions) + ", grantResults=" + Arrays.toString(grantResults) + ", permissionGrantResultMap=" + permissionGrantResultMap);
        boolean allGranted = true;
        StringBuilder builder = new StringBuilder("获取以下权限失败: \n");
        for (int i = 0; i < grantResults.length; i++) {
            int result = grantResults[i];
            if (result != PackageManager.PERMISSION_GRANTED) {
                String name = PermissionConstant.DANGER_PERMISSIONS_NAME_MAP.get(permissions[i]);
                if (StringUtil.isNotBlank(name)) {
                    builder.append(name).append(" ");
                }
                allGranted = false;
                //拒绝权限回调
                if (permissionGrantResultMap != null && permissionGrantResultMap.containsKey(permissions[i])) {
                    PermissionGrantResult permissionGrantResult = permissionGrantResultMap.get(permissions[i]);
                    if (permissionGrantResult != null && permissionGrantResult instanceof PermissionGrant2Result) {
                        ((PermissionGrant2Result) permissionGrantResult).permissionDenied(permissions[i]);
                    }
                }
            } else {
                //已经许可的权限
                if (permissionGrantResultMap != null && permissionGrantResultMap.containsKey(permissions[i])) {
                    PermissionGrantResult permissionGrantResult = permissionGrantResultMap.get(permissions[i]);
                    if (permissionGrantResult != null) {
                        permissionGrantResult.permissionGranted(permissions[i]);
                    }
                }
            }
            permissionGrantResultMap.remove(permissions[i]);
        }
        if (!allGranted) {
//            builder.append("\n应用可能无法正常运行");
            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 检查权限，执行授权任务
     */
    private void checkAllDangerPermissions() {
        List<PermissionCheck> permissionCheckList = new ArrayList<>();
        for (int i = 0; i < PermissionConstant.APP_DANGEROUS_PERMISSIONS.length; i++) {
            String permission = PermissionConstant.APP_DANGEROUS_PERMISSIONS[i];
            if (StringUtil.isNotBlank(permission)) {
                PermissionCheck permissionCheck = new PermissionCheck(permission);
                if (Manifest.permission.READ_PHONE_STATE.equals(permission)) {
                    //如果是可以读取deviceId，则可执行相关初始化回调
                    permissionCheck.permissionGrantResult = new PermissionGrantResult() {
                        @Override
                        public void permissionGranted(String permission) {
//                            Context context = BaseApplication.getContext();
//                            LogUtil.d("获取到READ_PHONE_STATE权限，执行软件更新和考拉初始化 deviceId=" + DeviceUtil.getDeviceId(context) + ", EcarxConfig deviceId=" + EcarxManager.getInstance().getEcarxConfig().getDeviceId());
//                            EcarxManager.getInstance().getEcarxConfig().setDeviceId(DeviceUtil.getDeviceId(context));
//                            LogUtil.d("EcarxConfig deviceId=" + EcarxManager.getInstance().getEcarxConfig().getDeviceId());
//                            updateVersion();
//                            KaolaUtil.getOpenid(BaseApplication.getContext());
                        }
                    };
                }

                if (Manifest.permission.READ_CONTACTS.equals(permission)) {
                    //上传通讯录
                    permissionCheck.permissionGrantResult = new PermissionGrantResult() {
                        @Override
                        public void permissionGranted(String permission) {
//                            Context context = BaseApplication.getContext();
//                            LogUtil.d("获取到READ_CONTACTS权限，执行上传通讯录EcarxConfig deviceId=" + EcarxManager.getInstance().getEcarxConfig().getDeviceId());
//                            // 上传通讯录
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ServerReqIM.upLoadContect();
//                                }
//                            }).start();
                        }
                    };
                }
                permissionCheckList.add(permissionCheck);
            }
        }
        if (permissionCheckList.size() > 0) {
            checkDangerPermissions(permissionCheckList);
        }
    }

}

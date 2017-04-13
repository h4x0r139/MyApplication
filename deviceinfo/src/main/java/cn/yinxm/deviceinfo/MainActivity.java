package cn.yinxm.deviceinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import cn.yinxm.lib.screen.ScreenUtil;
import cn.yinxm.lib.utils.DeviceUtil;
import cn.yinxm.lib.utils.LogUtil;

public class MainActivity extends FragmentActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        getDeviceInfo();
    }

    public void getDeviceInfo() {
        StringBuilder stringBuilder =  new StringBuilder();
       String str =  ScreenUtil.debugScreenInfo(this);
        if (!TextUtils.isEmpty(str)) {
            str = "屏幕配置：\n" + str + "\n\n";
        }
        stringBuilder.append(str);

        str = DeviceUtil.getDebugInfo();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append("设备信息：\n");
            stringBuilder.append(str).append("\n\n");

        }
        tv.setText(stringBuilder.toString());
        LogUtil.d(stringBuilder.toString());
    }

}

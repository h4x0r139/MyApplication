package cn.yinxm.ndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.data;
import static android.R.attr.switchMinWidth;
import static android.R.attr.vendor;
import static cn.yinxm.ndk.R.id.tv;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("ndk_jni_01");
//        java.lang.UnsatisfiedLinkError: dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/cn.yinxm.ndk-1/base.apk"],
//        nativeLibraryDirectories=[/data/app/cn.yinxm.ndk-1/lib/arm, /data/app/cn.yinxm.ndk-1/base.apk!/lib/armeabi-v7a, /vendor/lib, /system/lib]]] couldn't find "libndk_jni_01c.so"

    }

    Button btn1, btn2, btn3;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    public native String getStrFromJni();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                String key = KeyUtil.getKey();
                Log.d("yinxm", "key=" +key);
                tv.append("java call native getkey="+key+"\n");
                break;
            case R.id.btn2:
                key = KeyUtil.callJavaStaticMethod();
                Log.d("yinxm", "key=" +key);
                tv.append("native call java  callJavaStaticMethod="+key+"\n");
                break;
            case R.id.btn3:
                key = KeyUtil.callJavaObjectMethod();
                Log.d("yinxm", "key=" +key);
                tv.append("native call java  callJavaObjectMethod="+key+"\n");
                break;
        }
    }
}
